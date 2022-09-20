package ru.kurlyanchik.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kurlyanchik.model.User;

import java.util.Optional;

public interface UserService extends UserDetailsService{
     Optional<User> findByUsername(String username);
}
