package inflearn.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//@Configuration
//@EnableWebSecurity
public class SessionManagementSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();

        http.formLogin();

        http.sessionManagement() // 세션 관리 기능이 작동함(동시 세션 제어, 세션 고정 보호, 세션 정책)
                //.sessionFixation().changeSessionId() // 세션 고정 보호 => 로그인할 때마다 세션 ID 변경해서 전달(공격자가 사용자 쿠키로 인증하는 부분 방어)
                //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 스프링 시큐리티가 필요 시 생성(기본값), JWT 사용하는 경우에는 Stateless로 사용
                .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
                .maxSessionsPreventsLogin(false); // true로 하는 경우 동시 로그인 차단함, false(default값)로 하는 경우 기존 세션 만료

    }
}
