/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.controller;

import ac.encg.preins.service.UserService;
import ac.encg.preins.utility.SendMail;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author kudo
 */
@Controller
@Getter
@Setter
@Scope("session")
@Named("contactController")
public class ContactController implements Serializable {

    @Autowired
    private UserService userService;

    private String email = "";

    private String cne = "";

    private String text = "";

    private Logger logger = Logger.getLogger(getClass().getName());

    public void sendReclamation() {

        SendMail.sendRecalamation(cne, email, text);

        email = "";
        cne = "";
        text = "";
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Votre réclamation à été envoyé avec succes!"));
    }

}
