package com.resourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter
	{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http.authorizeRequests(authz -> authz.antMatchers(HttpMethod.GET,
		  "/foos/**").hasAuthority("SCOPE_message.read") .antMatchers(HttpMethod.POST,
		  "/foos").hasAuthority("SCOPE_message.write").anyRequest().authenticated())
		  .oauth2ResourceServer(oauth2 -> oauth2.jwt());
		 
			/*
			 * http .mvcMatcher("/foos/**") .authorizeRequests()
			 * .mvcMatchers("/foos/**").access("hasAuthority('SCOPE_message.read')") .and()
			 * .oauth2ResourceServer() .jwt();
			 */
	}
}
