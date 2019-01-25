package com.web.config;

import com.web.domain.enums.SocialType;
import com.web.oauth.ClientResources;
import com.web.oauth.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

import static com.web.domain.enums.SocialType.*;

@Configuration
@EnableWebSecurity  //웹에서 시큐리티 기능을 사용하겠다는 어노테이션
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //자동 설정 그대로 사용할 수 있지만 요청, 권한, 기타설정에는 필수적으로 최적화한 설정이 들어가야함
    //최석화 설정을 위해 WebSecurityConfigurerAdapter를 상속받음
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Override   //최적화 설정을 위해 configure(HttpSecurity http)메서드를 오버라이드하여 원하는 형식의 시큐리티 설정
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http.authorizeRequests()
                .antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**",
                        "/console/**").permitAll()
                .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(
                        "/login"))
                .and()
                .formLogin()
                .successForwardUrl("/board/list")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .addFilterBefore(oauth2Filter(), BasicAuthenticationFilter.class)
                .csrf().disable();

    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration( //현재의 요청과 context를 저장하는 첫번째 Bean을 생성하는 방법
            OAuth2ClientContextFilter filter ){ //OAuth2ClientContextFilter 현재의 요청(로그인)과 Context(URI)를 저장하는 Filter
        FilterRegistrationBean registration = new FilterRegistrationBean(); //OAuth2 클라이언트용 시큐리티 필터인 OAuth2ClientContextFilter를 불러와서 올바른 순서로 필터가 동작하도록 설정합니다.
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter oauth2Filter() {
        CompositeFilter filter = new CompositeFilter(); //복합적인 filter를 생성하기위한 Filter class
        List<Filter> filters = new ArrayList<>(); //facebook,google,kakao 로그인관련 3개의 filter생성
        filters.add(oauth2Filter(facebook(), "/login/facebook", FACEBOOK));
        filters.add(oauth2Filter(google(), "/login/google", GOOGLE));
        filters.add(oauth2Filter(kakao(), "/login/kakao", KAKAO));
        filters.add(oauth2Filter(naver(), "/login/naver", NAVER));
        filter.setFilters(filters);
        return filter;
    }

    private Filter oauth2Filter(ClientResources client, String path, SocialType socialType) {
        OAuth2ClientAuthenticationProcessingFilter filter =
                new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext); //client.getClient() client Id 값 ,access Token을 받아오는애
        filter.setRestTemplate(template);
        filter.setTokenServices(new UserTokenService(client, socialType));
        filter.setAuthenticationFailureHandler(((request, response, exception) ->
                response.sendRedirect("/error")));
        return filter;

    }

    @Bean   //소셜 미디어 리소스 정보는 시큐리티 설정에서 사용하기때문에 빈으로 등록
    @ConfigurationProperties("naver")
    // 3개이상의 소셜미디어 프로퍼티를 ConfigurationProperties 접두사를 이용하여 바인딩
    // ConfigurationProperties이 없으면 일일이 프로퍼티값을 불러와야함
    public ClientResources naver(){
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao() {
        return new ClientResources();
    }
}