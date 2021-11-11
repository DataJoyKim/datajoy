package com.d1.auth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.d1.auth.jwt.AccountService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AccountService accountService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* request path 제한 */
	    http.httpBasic().disable() // REST API만을 고려, 기본 설정 해제
	        .csrf().disable() // csrf 미사용
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션사용하지 않음
	        .and()
	        .authorizeRequests()
			.mvcMatchers("/**").permitAll()
			.mvcMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(accountService);
	}
}
