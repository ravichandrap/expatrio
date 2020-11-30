package com.expatrio.auth.service;

import com.expatrio.auth.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SecurityConfig //extends WebSecurityConfigurerAdapter
 {
//
////    @Autowired
////    DataSource dataSource;
//
//    @Autowired
//    @Qualifier("customUserDetailsService")
//    UserDetailsService userDetailsService;
//
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    AlwaysSendUnauthorized401AuthenticationEntryPoint alwaysSendUnauthorized401AuthenticationEntryPoint = new AlwaysSendUnauthorized401AuthenticationEntryPoint();
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        final String prfix = "/api/v1";
//        httpSecurity.csrf().disable()
//                .authorizeRequests()
//               // .antMatchers(HttpMethod.POST, "/**").permitAll();
////                .anyRequest().authenticated().and().
////                exceptionHandling().and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//
//    public class AlwaysSendUnauthorized401AuthenticationEntryPoint implements AuthenticationEntryPoint {
//        @Override
//        public final void commence(HttpServletRequest request, HttpServletResponse response,
//                                   AuthenticationException authException) throws IOException {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//
//    }
}
