package com.Cres.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cres.Model.User;
import com.Cres.Repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 

import org.springframework.context.annotation.Lazy; 

@Service
public class CresUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private JwtService jwtService;

    @Lazy // Prevents circular dependency error
    private AuthenticationManager authManager;

    @Autowired
    public CresUserDetailsService(UserRepository userRepository, JwtService jwtService, @Lazy AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
        this.authManager = authManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("User not found -loadbyusername");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String authenticateUser(User user) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        User authenticatedUser = (User) authentication.getPrincipal();
        return jwtService.generateToken(authenticatedUser);
    }
}
