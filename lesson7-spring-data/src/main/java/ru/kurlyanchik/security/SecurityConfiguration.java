package ru.kurlyanchik.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kurlyanchik.service.UserServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(
            AuthenticationManagerBuilder authBuilder,
            UserServiceImpl userService,
            PasswordEncoder encoder
    ) throws Exception {
        authBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password(encoder.encode("guest"))
                .roles("GUEST")
                .and()
                .withUser("manager")
                .password(encoder.encode("manager"))
                .roles("MANAGER")
                .and()
                .withUser("customer")
                .password(encoder.encode("customer"))
                .roles("CUSTOMER") ;
        authBuilder.userDetailsService(userService);
    }

}
