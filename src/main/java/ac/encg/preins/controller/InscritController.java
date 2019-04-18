package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Bac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
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

    private Inscrit inscrit = new Inscrit();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<Bac>bacs = new ArrayList<>();
    private List<Academie>academies = new ArrayList<>();
    // private List<String> anneeBac =  Arrays.asList("2010","2011","2012","2014","2015","2016","2017","2018","2019");

    public void save() {
        inscritService.save(inscrit);

    }

    @PostConstruct
    public void loadProvinces() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        bacs = inscritService.loadBacs();
        academies = inscritService.loadAcademies();
        inscrit.setProvinceNaiss(new Province());
        inscrit.setPaysNaiss(new Pays());

    }

}
