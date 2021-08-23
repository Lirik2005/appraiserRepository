package com.example.appraiserbase.security;

import com.example.appraiserbase.model.Appraiser;
import com.example.appraiserbase.repository.appraiserRepository.AppraiserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppraiserRepository appraiserRepository;

    public UserDetailsServiceImpl(AppraiserRepository appraiserRepository) {
        this.appraiserRepository = appraiserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Appraiser appraiser = appraiserRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(appraiser);
    }
}
