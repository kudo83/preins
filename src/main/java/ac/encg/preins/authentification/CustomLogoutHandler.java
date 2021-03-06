/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.authentification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author kudo
 */
@Component
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    // Just for setting the default target URL
//    public CustomLogoutHandler(String defaultTargetURL) {
//        this.setDefaultTargetUrl(defaultTargetURL);
//    }
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

//        ServletContext servletContext = (ServletContext) FacesContext
//                .getCurrentInstance().getExternalContext().getContext();
//        Bean bean = (Bean) servletContext.getAttribute("inscritController");
//        bean = null;

        SecurityContextHolder.clearContext();
        super.onLogoutSuccess(request, response, authentication);

    }
}
