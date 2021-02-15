package com.ebi.springboot.config;

import static com.ebi.springboot.util.PersonConstants.USER1_NAME;
import static com.ebi.springboot.util.PersonConstants.USER2_NAME;
import static com.ebi.springboot.util.PersonConstants.USER_ROLE;
import static com.ebi.springboot.util.PersonConstants.MANAGER_ROLE;
import static com.ebi.springboot.util.PersonConstants.READ_PRIVILEGES;
import static com.ebi.springboot.util.PersonConstants.WRITE_PRIVILEGES;
import static com.ebi.springboot.util.PersonConstants.PASSWORD;
import static com.ebi.springboot.util.PersonConstants.MANAGER_USERNAME;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Spring security configuration
 * @author Yogesh Patil
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
     * configures an in memory authentication manager. In the real world app, We shall have a custom service that
     * fetches users from a store (RDBMS or NoSQL)
     *
     * @param auth authentication manager to configure
     *
     * @throws Exception
     */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode(PASSWORD);

        auth.inMemoryAuthentication()
                .withUser(USER1_NAME)
                .password(password)
                .roles(USER_ROLE)

                .and()

                .withUser(USER2_NAME)
                .password(password)
                .roles(USER_ROLE)
                .authorities(READ_PRIVILEGES)

                .and()

                .withUser(MANAGER_USERNAME)
                .password(password)
                .roles(MANAGER_ROLE)
                .authorities(WRITE_PRIVILEGES, READ_PRIVILEGES);
    }
	
	  /**
     * Password encoder bean
     *
     * @return a PasswordEncoder that uses the BCrypt strong hashing function
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    /**
     * Configures the HTTP security with stateless session as we will be using HTTP Basic auth mechanism.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() // Disabling CSRF as we will be using HTTP Basic
                .authorizeRequests()

                .antMatchers("/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll() // permit all swagger related content with no auth
                .anyRequest().authenticated()

                .and()
                .httpBasic()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
