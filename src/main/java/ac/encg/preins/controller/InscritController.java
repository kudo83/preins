package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.service.InscritService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;

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
    private List<SerieBac>seriesBac = new ArrayList<>();
    private List<Academie>academies = new ArrayList<>();
    // private List<String> anneeBac =  Arrays.asList("2010","2011","2012","2014","2015","2016","2017","2018","2019");

    public void save() {
        inscritService.save(inscrit);

    }

    @PostConstruct
    public void loadProvinces() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();

    }
    
    
    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
//        try {
//            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
 
    }
    public void copyFile(String fileName, InputStream in) {
        try {
 
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File("" + fileName));
 
            int read = 0;
            byte[] bytes = new byte[2024];
 
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
 
            in.close();
            out.flush();
            out.close();
 
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
