package ac.encg.preins.controller;

import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.nonPersistable.LoggedUser;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import ac.encg.preins.service.UserService;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@Scope("session")
@Named("loggedUserController")
public class LoggedUserController implements Serializable {

    @Autowired
    private UserService userService;
   public String loggedUserName(){
        LoggedUser loggedUser = UserHelper.getLoggedUser(userService.getAuthentication());
       
       return loggedUser.getUsername();
       
   }
}
