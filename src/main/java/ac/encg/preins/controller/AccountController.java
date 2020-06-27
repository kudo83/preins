/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.controller;

import ac.encg.preins.entity.Role;
import ac.encg.preins.entity.User;
import ac.encg.preins.entity.VerificationToken;
import ac.encg.preins.service.UserService;
import ac.encg.preins.utility.SendMail;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author kudo
 */
@Controller
@Getter
@Setter
@ViewScoped
@Named("accountController")
public class AccountController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User registeredUser = new User();

    private String submittedToken;

    private Logger logger = Logger.getLogger(getClass().getName());

    public void registerNewUser() {

        Optional<User> optional = userService.getUser(registeredUser.getUsername());
        if (optional.isPresent()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Un compte avec le même email existe déjà!", null));
            logger.info("Un compte avec le même email existe déjà: " + registeredUser.getUsername());
            return;
        } else {
            Set<Role> roles = new HashSet<>();
            Role userRole = userService.getRole("USER");
            roles.add(userRole);
            registeredUser.setRoles(roles);
            String encodedPassword = passwordEncoder.encode(registeredUser.getPassword());
            registeredUser.setPassword(encodedPassword);
            userService.save(registeredUser);
            String token = UUID.randomUUID().toString();
            userService.createVerificationToken(registeredUser, token);
            System.out.println("Sending mail . . .");
            SendMail.sendEmailConfirmationMail(registeredUser, token);
            System.out.println("Success");
            registeredUser = new User();
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Un email de vérification a été envoyé à votre adresse email!", null));

        }

    }

    public void confirmRegistration() {
        VerificationToken verificationToken = userService.getVerificationToken(submittedToken);
        if (verificationToken == null) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le code d'activation est invalide!", null));
        } else {
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userService.save(user);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre compte est activé avec Succès", null));
        }
    }

}
