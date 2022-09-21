package ru.kurlyanchik.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kurlyanchik.model.User;
import ru.kurlyanchik.model.dto.UserDto;
import ru.kurlyanchik.model.mapper.UserDtoMapper;
import ru.kurlyanchik.repositories.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService  {

     private final UserDtoMapper mapper;
     private final UserRepository userRepository;
     private final PasswordEncoder encoder;
     private UserDto dto;

     public org.springframework.security.core.userdetails.User findUserByUsername(String username) {
          return userRepository.findByUsername(username)
                  .map(user -> new org.springframework.security.core.userdetails.User(
                          user.getUsername(),
                          user.getPassword(),
                          user.getRoles().stream()
                                  .map(role -> new SimpleGrantedAuthority(role.getName()))
                                  .collect(Collectors.toList())
                  )).orElseThrow(() -> new UsernameNotFoundException(username));
     }

     public void save(UserDto dto) {
          userRepository.save(mapper.map(dto, encoder));
     }

     /*   public Optional<UserDto> findUserById(Long id) {
            return userRepository.findById(id).map(mapper::map);
        }*/
     public void deleteUserById(Long id) {
          userRepository.deleteById(id);
     }
}
