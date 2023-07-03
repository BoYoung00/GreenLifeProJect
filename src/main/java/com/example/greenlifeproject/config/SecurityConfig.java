package com.example.greenlifeproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//시큐리티 설정을 이 클래스에서 한다고 선언
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 경로 권한 설정
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/css/**", "/img/**").permitAll() // 추가된 부분
                .anyRequest().permitAll()
                .and()
                .formLogin()
                // 로그인 페이지 설정
                .loginPage("/members/login")
                // 로그인 처리 URL 설정
                .loginProcessingUrl("/login")
                // 로그인 성공 시 이동할 URL 설정
                .defaultSuccessUrl("/")
                // 로그인 실패 시 이동할 URL 설정
                .failureUrl("/members/loginError")
                .and()
                .logout()
                // 로그아웃 URL 설정
                .logoutUrl("/logout")
                // 로그아웃 성공 시 이동할 URL 설정
                .logoutSuccessUrl("/members/login"); // 추가된 부분
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
