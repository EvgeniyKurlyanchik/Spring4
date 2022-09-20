package ru.kurlyanchik.service;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Data
@Service
public class SecurityServiceImpl implements UserDetailsService {
    private  static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private final UserService userService;
    private AuthenticationManager authenticationManager;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Data
@Service
public class SecurityServiceImpl implements SecurityService {
    private  static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
@Autowired
    private UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;
    @Override
>>>>>>> origin/lesson12
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof UserDetails){
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    @Override
<<<<<<< HEAD
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserByUsername(username);
=======
    public void autoLogin(String username, String password) {
UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
logger.debug(String.format("Successfully %s auto lggged in", username));
        }
>>>>>>> origin/lesson12
    }
}
