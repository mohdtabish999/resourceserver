# resourceserver

Note: If running inside docker use below command to add binding, otherwise port will not be mapped(already done in run3.sh)
````
./standalone.sh -Djboss.socket.binding.port-offset=0 -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=$DIR/config/oauth2-sample-realm-config.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING
````

See

https://github.com/jgrandja/spring-security-oauth-5-2-migrate

https://github.com/Baeldung/spring-security-oauth/tree/master/oauth-resource-server

We need authorization server to get access code, we will be using opensource jboss keycloak

set offset to 0, So it will start at 8080 port, see run.sh in keycloak project

````
./add-user-keycloak.sh -r oauth2-sample -u user1 -p password
./standalone.sh -Djboss.socket.binding.port-offset=0 -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=singleFile -Dkeycloak.migration.file=$DIR/oauth2-sample-realm-config.json -Dkeycloak.migration.strategy=OVERWRITE_EXISTING
````
Once started, visit http://localhost:8080/auth
Now start resource server

#access token
````
OPTION 1: from command line

curl -L -X POST 'http://localhost:8080/auth/realms/oauth2-sample/protocol/openid-connect/token' \
-H 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=messaging-client' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'client_secret=secret' \
--data-urlencode 'scope=message.read' \
--data-urlencode 'username=user1' \
--data-urlencode 'password=password'

Or from post man

Option 2a : Use Authorization basic auth username password and in body choose  x-www-form-urlencoded and below property

POST http://localhost:8080/auth/realms/oauth2-sample/protocol/openid-connect/token

grant_type:password
scope:message.read
username:user1
password:password

Option 2b : Use everything in body 

POST http://localhost:8080/auth/realms/oauth2-sample/protocol/openid-connect/token

Content-Type:application/x-www-form-urlencoded
client_id:messaging-client
grant_type:password
client_secret:secret
scope:message.read
username:user1
password:password

````


#call protected resource
set Authorization header as
````
-H "Authorization : Bearer <access_token>

or set bearer token directly in authorization bar
````


