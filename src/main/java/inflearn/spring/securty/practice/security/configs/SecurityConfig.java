package inflearn.spring.securty.practice.security.configs;

import inflearn.spring.securty.practice.security.handler.CustomAccessDeniedHandler;
import inflearn.spring.securty.practice.security.handler.CustomAuthenticationFailureHandler;
import inflearn.spring.securty.practice.security.handler.CustomAuthenticationSuccessHandler;
import inflearn.spring.securty.practice.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private final AuthenticationDetailsSource authenticationDetailsSource;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // js / css / image ?????? ??? ?????? ????????? ????????? ????????? ?????? ???????????? ?????? (?????? ????????? ?????????)
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // ???????????? ??????????????? ??????(??????????????? ???????????? ????????? ????????? ??????)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // ????????? ?????? : {id}encodedPassword
        // ?????? ????????? Bcrypt : {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
        // ???????????? ?????? : Bcrypt, sha256 ???
        // PasswordEncoder ?????????????????? ???????????? ???????????? ???????????? ??????????????? ????????? ????????????. encode(????????????) : ???????????? ????????? / matches(rawPassword, encoderPassword) : ???????????? ??????
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users", "user/login/**", "/login**").permitAll()
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGE")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()

        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                //.authenticationDetailsSource(authenticationDetailsSource) // ?????????, ???????????? ?????? ???????????? ??????
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .defaultSuccessUrl("/")
                .permitAll()
        .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
        ;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");
        return accessDeniedHandler;
    }
}
