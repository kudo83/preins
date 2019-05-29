/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import ac.encg.preins.nonPersistable.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author kudo
 */
public class UserHelper {

    public static LoggedUser getLoggedUser() {
        LoggedUser user = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = new LoggedUser();

            if (principal instanceof UserDetails) {
                user.setUsername(((UserDetails) principal).getUsername());
                user.setPassword(((UserDetails) principal).getPassword());
            } else {
                user.setUsername(principal.toString());
            }
        }
        return user;

    }
}
