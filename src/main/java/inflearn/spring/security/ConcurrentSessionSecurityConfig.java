package inflearn.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConcurrentSessionSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();

        http.formLogin();

        http.sessionManagement() // 세션 관리 기능이 작동함(동시 세션 제어, 세션 고정 보호, 세션 정책)
                .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
                .maxSessionsPreventsLogin(false); // false 하는 기존 사용자의 세션을 만료시킴
                                                  // 기존 사용자가 로그인을 하는 경우 ConcurrentSessionFilter에 의해서 session.isExpired()를 통해서 세션 만료 여부 체크
                                                  // 세션 만료 여부가 true인 경우, 로그아웃 처리 및 즉시 오류 페이지 응답 처리
    }
}
