package com.angular.angular.config;

import com.angular.angular.custom.CustomUserDetailsServiceImpl;
import com.angular.angular.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // This method is for overriding the default AuthenticationManagerBuilder.
    // We can specify how the user details are kept in the application. It may
    // be in a database, LDAP or in memory.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    // Changing default login url
    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/account/login");
        return authenticationFilter;
    }

    // For Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
//                cors().and()
//                .csrf().disable()
//                .authorizeRequests((authorizeRequest) -> {
//                    authorizeRequest
//                            .antMatchers("/account/**").permitAll();
////                            .antMatchers("/login").permitAll();
//                })
//                .requestCache((requestCache) -> {
//                    requestCache.disable();
//                })
//                .httpBasic();
        cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/account/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/account").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/categories").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/product").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/product").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .httpBasic();
    }

}
