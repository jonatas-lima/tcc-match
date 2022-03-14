package com.psoft.match.tcc.service.auth;

import com.psoft.match.tcc.model.user.TCCMatchUser;
import com.psoft.match.tcc.repository.user.UserRepository;
import com.psoft.match.tcc.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TCCMatchUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new AuthenticatedUser(user);
    }

}
