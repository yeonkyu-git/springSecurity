package co.yeonkyu.springsecurity.config;

import co.yeonkyu.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security를 활성화한다는 의미의 어노테이션
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");   // 인증을 무시한다. (static 파일은 무조건 접근 가능해야한다)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/signup", "/user").permitAll()  // 누구나 접근 가능
                    .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                    .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
                    .anyRequest().authenticated()  // 나머지 요청들은 권한의 종류에 상관없이 권한이 있어야 접근 가능
                .and()
                    .formLogin()
                        .loginPage("/login")  // 로그인 페이지 링크
                        .defaultSuccessUrl("/")  // 로그인 성공 후 리다이렉트 주소
                .and()
                    .logout()
                        .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                        .invalidateHttpSession(true)  // 세션 날리기
        ;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {   // 로그인할 때 필요한 정보를 가져오는 곳
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());   // Password 인코더
    }
}
