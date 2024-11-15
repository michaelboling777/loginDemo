package edu.missouristate.config;
import edu.missouristate.repository.UserRepository;
import edu.missouristate.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
     @Autowired
 private CustomUserDetailsService userDetailsService;
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 auth.userDetailsService(userDetailsService)
 .passwordEncoder(getPasswordEncoder());
 }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")
        ;
        http.authorizeRequests()
                .antMatchers("/admin/**", "/student/**").authenticated()
                .antMatchers("/legal/**", "/error/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll();
    }
    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}