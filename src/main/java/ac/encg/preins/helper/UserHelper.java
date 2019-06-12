/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import ac.encg.preins.nonPersistable.LoggedUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author kudo
 */
public class UserHelper {

    public static LoggedUser getLoggedUser(Authentication authentication) {
        LoggedUser user = null;
        if (authentication != null
                && authentication.isAuthenticated()
                && //when Anonymous Authentication is enabled
                !(authentication instanceof AnonymousAuthenticationToken)) {

            Object principal = authentication.getPrincipal();
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

    public static boolean isRoleUser(Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            List<String> roles = new ArrayList<String>();

            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (roles.contains("ROLE_USER")) {
                return true;
            }
        }

        return false;
    }
}
