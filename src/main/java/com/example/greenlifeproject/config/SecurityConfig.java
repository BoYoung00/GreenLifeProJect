package com.example.greenlifeproject.config;

import com.example.greenlifeproject.config.auth.LoginFailHandler;
import com.example.greenlifeproject.config.auth.PrincipalDetailsService;
import com.example.greenlifeproject.config.auth.oath.PrincipalOath2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity//시큐리티 설정을 이 클래스에서 한다고 선언
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true)

//소셜 로그인 순서
// 1.코드 받기(인증)
// 2.엑세스 토큰(권한)
// 3.사용자 프로필 정보를 가지고 와서
// 4.그 정보를 토대로 회원가입을 자동으로 진행또는 추가 정보
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOath2UserService principalOath2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 경로 권한 설정
                .antMatchers("/user/**").authenticated()
                .antMatchers("/survey/**").authenticated()
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
                .failureHandler(loginFailHandler())
                .and()
                .logout()
                // 로그아웃 URL 설정
                .logoutUrl("/logout")
                // 로그아웃 성공 시 이동할 URL 설정
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/members/login")
                .userInfoEndpoint()
                .userService(principalOath2UserService);//코드X  엑세스토큰 + 사용자 프로필정보만 가지고오기

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }
}
