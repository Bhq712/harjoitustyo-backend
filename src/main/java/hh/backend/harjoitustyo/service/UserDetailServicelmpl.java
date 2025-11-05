package hh.backend.harjoitustyo.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.backend.harjoitustyo.domain.User;
import hh.backend.harjoitustyo.domain.UserRepository;

@Service
public class UserDetailServicelmpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServicelmpl(UserRepository userRepository) {
       this.userRepository = userRepository; 
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUsername(username);
        
        if (currentUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        UserDetails user = new org.springframework.security.core.userdetails.User(
            username, 
            currentUser.getPassword(),
            AuthorityUtils.createAuthorityList(currentUser.getRole())
        );

        return user;
    }

}
