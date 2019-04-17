package ac.encg.preins.controller;

import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Province;
import ac.encg.preins.service.InscritService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@ManagedBean(name = "inscritController")
public class InscritController implements Serializable {
    
    @Autowired
    private InscritService inscritService;

//   private InscritService inscritService = new InscritService();
    private Inscrit inscrit = new Inscrit();
    ;
    
    private List<Province> provinces = new ArrayList<>();
    
    public void save() {
        inscritService.save(inscrit);
        
    }
    
    @PostConstruct
    public void loadProvinces() {
        provinces =  inscritService.loadProvinces();
        inscrit.setProvinceNaiss(new Province());
    }

//    public void initAutowairing() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        ServletContext servletContext = (ServletContext) externalContext.getContext();
//        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
//                getAutowireCapableBeanFactory().
//                autowireBean(this);
//    }
}
