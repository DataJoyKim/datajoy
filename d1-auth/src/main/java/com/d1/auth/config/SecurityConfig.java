package com.d1.auth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.d1.auth.jwt.JwtAuthenticationFilter;
import com.d1.auth.jwt.JwtTokenProvider;
import com.d1.auth.jwt.service.AccountService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AccountService accountService;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* request path 제한 */
	    http.authorizeRequests()
			.mvcMatchers("/auth/v1/").permitAll()
			.mvcMatchers("/auth/v1/test").hasRole("ADMIN")
			.mvcMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
	    
	    /* jwt 설정 */
		http.httpBasic().disable() // REST API만을 고려, 기본 설정 해제
	        .csrf().disable() // csrf 미사용
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션사용하지 않음
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt filter add
	    
		//http.formLogin(); // form login 인증방식 사용
		//http.httpBasic(); // http basic 인증방식 사용
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
