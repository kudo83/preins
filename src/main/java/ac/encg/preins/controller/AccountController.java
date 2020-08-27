/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.controller;

import ac.encg.preins.entity.PasswordResetToken;
import ac.encg.preins.entity.Role;
import ac.encg.preins.entity.User;
import ac.encg.preins.entity.VerificationToken;
import ac.encg.preins.service.UserService;
import ac.encg.preins.utility.SendMail;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author kudo
 */
@Controller
@Getter
@Setter
@Scope("session")
@Named("accountController")
public class AccountController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User registeredUser = new User();

    private String submittedToken;

//    private Logger logger = Logger.getLogger(getClass().getName());

    public void registerNewUser() {

        Optional<User> optional = userService.getUser(registeredUser.getUsername());
        if (optional.isPresent()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Un compte avec le même email existe déjà!"));
//            logger.info("Un compte avec le même email existe déjà: " + registeredUser.getUsername());
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
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Un email de vérification a été envoyé à votre adresse email!"));

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Merci aussi de vérifier le dossier du spam si vous ne trouver pas l'email!", ""));
        }

    }

    public void confirmRegistration() {
        VerificationToken verificationToken = userService.getVerificationToken(submittedToken);
        if (verificationToken == null) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le code d'activation est invalide!"));
        } else {
            User user = verificationToken.getUser();
            if (user.isEnabled()) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Vous avez déjà activé votre compte !"));
            } else {
                user.setEnabled(true);
                userService.save(user);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info!" , "Votre compte est activé avec Succès"));
            }
        }
    }

    public void resetPassword() {

        Optional<User> optional = userService.getUser(registeredUser.getUsername());
        if (!optional.isPresent()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur!" , "Cet Email ne coresspond à aucun utilisateur inscrit!"));
            return;
        } else {
            User user = optional.get();
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            SendMail.sendPasswordUpdateMail(user, token);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Veuillez consulter votre boite email!"));

        }

    }

    public void updatePassword() throws IOException {
//        Optional<User> optional = userService.getUser(registeredUser.getUsername());
        PasswordResetToken passwordToken = userService.getPasswordResetToken(submittedToken);
        if (passwordToken == null) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Avertissement!", "Un erreur interne s'est produit. Veuillez réessayer!"));
            return;
        } else {
//            User user = optional.get();
            String encodedPassword = passwordEncoder.encode(registeredUser.getPassword());
//            user.setPassword(encodedPassword);
            passwordToken.getUser().setPassword(encodedPassword);
            passwordToken.setUsed(true);
            userService.savePasswordResetToken(passwordToken);
            userService.save(passwordToken.getUser());
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Votre mot de passe a été mis à jours. Veuillez vous connecter!"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    //Monter la page login si le token n'est pas valide
    public void showChangePasswordPage() throws IOException {
        PasswordResetToken passwordToken = userService.getPasswordResetToken(submittedToken);
        if (passwordToken == null || passwordToken.isUsed()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Token invalide!"));

            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            registeredUser = passwordToken.getUser();
        }

    }

    public void doLogin() throws ServletException, IOException {
         ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

    }
    public void init(){
        registeredUser = new User();
    }

}
