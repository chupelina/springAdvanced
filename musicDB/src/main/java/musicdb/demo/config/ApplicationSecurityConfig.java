package musicdb.demo.config;

import musicdb.demo.services.implementations.MusicDBUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final MusicDBUserService musicDBUserService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, MusicDBUserService musicDBUserService) {
        this.passwordEncoder = passwordEncoder;
        this.musicDBUserService = musicDBUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/**",
                "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/home", "/users/login", "/users/register").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
        .defaultSuccessUrl("/home").failureForwardUrl("/users/login-error");
        //ToDo: login & register
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(musicDBUserService)
                .passwordEncoder(passwordEncoder);
    }
}
