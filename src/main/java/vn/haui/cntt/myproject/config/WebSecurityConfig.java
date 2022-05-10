package vn.haui.cntt.myproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    private static final String KEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http
                //.sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable the default session cookie; we'll be using a JWT authentication cookie instead.
                //.and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager())) // This filter intercepts the login, authenticates the user, and creates a JWT token in a cookie to authorize subsequent requests..
                //.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class) // This filter reads the JWT token from the cookie on subsequent requests, and authorizes the user (or not).
                .authorizeRequests()
                //.antMatchers("/list-users").authenticated() // need login when access .../list_users
                .antMatchers("/account/**", "/cart/**", "/order/**","/address/**","/create-address","/update-address/**","/delete-address",
                        "/default-address", "/create-order", "/save-order").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("psw")
                    .defaultSuccessUrl("/home")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/doLogin");
                    }
                })
                    .failureUrl("/404")
                .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/404")
                .and()
                .rememberMe()
                    .key(KEY)
                    .tokenValiditySeconds(3*24*60*60);
    }
}
