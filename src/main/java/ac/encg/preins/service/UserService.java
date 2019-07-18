package ac.encg.preins.service;

import ac.encg.preins.entity.Role;
import ac.encg.preins.entity.User;
import ac.encg.preins.entity.VerificationToken;
import ac.encg.preins.repository.RoleRepository;
import ac.encg.preins.repository.TokenRepository;
import ac.encg.preins.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kudo
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TokenRepository tokenRepo;
    
    @Autowired
    private RoleRepository roleRepo;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     *
     * @param users
     */
    public void saveUsers(List<User> users) {
        userRepo.saveAll(users);

    }

    public Optional<User> getUser(String email) {
        return userRepo.findByUsername(email);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken newUserToken = new VerificationToken(token, user);
        tokenRepo.save(newUserToken);
    }

    public VerificationToken getVerificationToken(String token) {
       return tokenRepo.findByToken(token);
    }

    public Role getRole(String roleLib) {
       return roleRepo.findByLib(roleLib);
    }

}
