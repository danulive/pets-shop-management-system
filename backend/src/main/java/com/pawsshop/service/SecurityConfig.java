package com.pawsshop.service;
import com.pawsshop.repo.ShopUserRepository; import org.springframework.context.annotation.*; import org.springframework.http.HttpMethod; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService users(ShopUserRepository repo){return name->repo.findByUsername(name).map(u->org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPasswordHash()).roles(u.getRole()).build()).orElseThrow(()->new UsernameNotFoundException("User not found"));}
 @Bean SecurityFilterChain filter(HttpSecurity http)throws Exception {http.csrf().disable().authorizeRequests()
 .antMatchers(HttpMethod.POST,"/api/messages").permitAll().antMatchers(HttpMethod.GET,"/api/catalog","/api/services","/api/doctors").permitAll()
 .antMatchers("/api/admin/**","/api/users/**","/api/messages/**").hasRole("ADMIN")
 .antMatchers("/api/doctor/**").hasRole("DOCTOR").antMatchers("/api/customer/**").hasRole("CUSTOMER")
 .antMatchers("/api/me").authenticated().antMatchers("/api/**").hasAnyRole("ADMIN","STAFF")
 .anyRequest().permitAll().and().httpBasic();return http.build();}
}
