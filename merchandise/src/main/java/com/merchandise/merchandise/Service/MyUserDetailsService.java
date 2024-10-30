package com.merchandise.merchandise.Service;


import com.merchandise.merchandise.Model.User;
import com.merchandise.merchandise.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository Userepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Reemplaza este método para buscar el usuario en tu base de datos o sistema de usuarios
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =Userepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found "+ username));
        String encodedPassword = passwordEncoder.encode(user.getPass());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(encodedPassword)
                .build();

    }
}
