package com.nology.Jobs4;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
		.withUser("temp")
		.password("password")
		.roles("TEMP")
		.and()
//		can do chaining with .and()
		.withUser("admin")
		.password("password")
		.roles("ADMIN"); 
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance(); 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/profile", "/temps", "/jobs").hasRole("TEMP")
			.antMatchers("/").permitAll()
			.and().formLogin();
		
		// alternatives 
		//.hasAnyRole()
		//.hasRole("ADMIN")
		//.permitAll()
		//.antMatchers("/**") wild card any link
		// most restrictive to least 
	}
	
	
	

}
