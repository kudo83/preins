package ac.encg.preins.service;

import ac.encg.preins.entity.User;
import ac.encg.preins.repository.UserRepository;
import ac.encg.preins.utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User optionalUser = userRepository.findByName(username);

//        optionalUser
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return optionalUser
//                .map(CustomUserDetails::new).get();
return new CustomUserDetails(optionalUser);
    }
}
