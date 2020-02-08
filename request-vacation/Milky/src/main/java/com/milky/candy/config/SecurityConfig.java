package com.milky.candy.config;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*
     final UserService userService;
    
     @Override
     protected void configure(HttpSecurity http) throws Exception {
          http
               .csrf().disable()
               .authorizeRequests()
                    .antMatchers("/user/login").permitAll()
                    .antMatchers("/user").hasAuthority("USER")
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
//               .formLogin()
//                    .and()
               .logout()
               ;              
     }
    
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userService)
               .passwordEncoder(userService.passwordEncoder());
     }
    
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
          return super.authenticationManagerBean();
     }
     */
}