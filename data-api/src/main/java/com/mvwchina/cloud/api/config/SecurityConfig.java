package com.mvwchina.cloud.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**","/index","/login");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http
		.authorizeRequests()
		.antMatchers("/index").permitAll()		
		.antMatchers("/user/**").hasRole("USER")
		.and()
		 //.csrf().disable() //关闭CSRF
        .csrf().ignoringAntMatchers("/druid/**")
        .and()
		.formLogin()
		.loginPage("/login").failureUrl("/login-error");
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())//在此处应用自定义PasswordEncoder
        .withUser("admin")
        .password("admin")
        .roles("USER");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	  return NoOpPasswordEncoder.getInstance();
	}
	
}
