package ac.encg.preins.service;


import ac.encg.preins.entity.User;
import ac.encg.preins.repository.UserRepository;
import java.util.List;
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

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    /**
     *
     * @param users
     */
    public void saveUsers(List<User> users){
      userRepo.saveAll(users);
              
    }
    
    
}
