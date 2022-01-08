package com.d1.goalset.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    /* jwt 설정 */
		http.httpBasic().disable() // REST API만을 고려, 기본 설정 해제
	        .csrf().disable() // csrf 미사용
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션사용하지 않음
	        .and()
	        .authorizeRequests()
			.mvcMatchers("/**").permitAll()
			.mvcMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
			//.and()
			//addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt filter add
	}
}
