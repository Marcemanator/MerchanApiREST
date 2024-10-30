package com.merchandise.merchandise.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IMyUserDetailsService {

    public UserDetails loadUserByUsername(String username);
}
