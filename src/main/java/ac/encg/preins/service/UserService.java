package ac.encg.preins.service;

import ac.encg.preins.entity.PasswordResetToken;
import ac.encg.preins.entity.QUser;
import ac.encg.preins.entity.Role;
import ac.encg.preins.entity.User;
import ac.encg.preins.entity.VerificationToken;
import ac.encg.preins.repository.PasswordTokenRepository;
import ac.encg.preins.repository.RoleRepository;
import ac.encg.preins.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ac.encg.preins.repository.VerificationTokenRepository;

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
    private VerificationTokenRepository verificationTokenRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordTokenRepository passwordTokenRepo;

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
        verificationTokenRepo.save(newUserToken);
    }

    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepo.findByToken(token);
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordTokenRepo.findByToken(token);
    }

    public Role getRole(String roleLib) {
        return roleRepo.findByLib(roleLib);
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepo.save(myToken);
    }
    
    public void savePasswordResetToken(PasswordResetToken token){
        passwordTokenRepo.save(token);
    }
    
    public Iterable<User> findOperateurs(){
        Role roleOperateur = roleRepo.getOne(Long.valueOf(2));
        return userRepo.findAll(QUser.user.roles.contains(roleOperateur));
    }

}
