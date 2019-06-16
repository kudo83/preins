package ac.encg.preins.controller;

import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.nonPersistable.LoggedUser;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import ac.encg.preins.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@ManagedBean(name = "loggedUserController")
public class LoggedUserController implements Serializable {

    @Autowired
    private UserService userService;
   public String loggedUserName(){
        LoggedUser loggedUser = UserHelper.getLoggedUser(userService.getAuthentication());
       
       return loggedUser.getUsername();
       
   }
}
