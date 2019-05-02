package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.service.InscritService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.UploadedFile;

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

    private Part uploadedPhotoFile;
    private Part uploadedCinFile;
    private String tempFolder = "D:\\PreinsTemp";
    private String uploadFolder = "D:\\PreinsUploads";
    private UploadedFile cinFile;

    private Inscrit inscrit = new Inscrit();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();
    String photoFileName = "default.gif";
    String cinFileName = "id.png";

    public void save() {
        boolean retour = false;
        if (photoFileName.equals("default.gif") ){
           FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo Personnelle est obligatoire", null));
           retour = true;
        }
        if (cinFileName.equals("id.png")){
           FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo CIN est obligatoire", null));
           retour = true;
        } 
        if (retour){
            return;
        }
            
        try {
            Files.copy(Paths.get(tempFolder+"\\"+photoFileName),Paths.get(uploadFolder+"\\"+inscrit.getCne()+"P"+getFileExtension(photoFileName)));
            Files.delete(Paths.get(tempFolder+"\\"+photoFileName));
            
            Files.copy(Paths.get(tempFolder+"\\"+cinFileName),Paths.get(uploadFolder+"\\"+inscrit.getCne()+"C"+getFileExtension(cinFileName)));
            Files.delete(Paths.get(tempFolder+"\\"+cinFileName));
            
        } catch (IOException ex) {
            Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        inscritService.save(inscrit);
        
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre Inscription à été faite avec succès!", null));
        

    }

    public void validate() {
        
    }

    @PostConstruct
    public void loadProvinces() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();

    }

    public void uploadPhoto() {

        transferFile(uploadedPhotoFile, tempFolder);
        photoFileName = uploadedPhotoFile.getSubmittedFileName();;
    }

    public void uploadCin() {
        transferFile(uploadedCinFile, tempFolder);
        cinFileName = uploadedCinFile.getSubmittedFileName();;

    }

    public void transferFile(Part uploadedFile, String folder) {

        InputStream input = null;
        try {
            input = uploadedFile.getInputStream();
            {
                String inputName = uploadedFile.getSubmittedFileName();
                Files.copy(input, new File(folder, inputName).toPath());

            }
        } catch (IOException ex) {
            Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      }  
    
    public String getFileExtension(String fileName) {
            String extension = "";
        try{
            
 
                extension = fileName.substring(fileName.lastIndexOf("."));
            
        } catch (Exception e) {
            extension = "";
        }
 
        return extension;
 
    }

    
}
