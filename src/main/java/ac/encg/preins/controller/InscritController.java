package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Etape;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.helper.InscritHelper;
import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.service.InscritService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import ac.encg.preins.nonPersistable.Civilite;
import ac.encg.preins.nonPersistable.LoggedUser;
import ac.encg.preins.nonPersistable.Sex;
import org.primefaces.event.FileUploadEvent;
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
@ViewScoped
public class InscritController implements Serializable {

    @Autowired
    private InscritService inscritService;
    private String tempFolder = "D:\\PreinsTemp\\";
    private String uploadFolder = "D:\\PreinsUploads\\";
    private UploadedFile uploadedPhoto;
    private UploadedFile uploadedCin;
    private InputStream inputStreamPhoto;
    private String photoContentsAsBase64;

    private Inscrit inscrit = new Inscrit();
    private List<Etape> etapes = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();
    private List<Civilite> civilites = new ArrayList<>();
     private List<Sex> sex = new ArrayList<>();
    
    private String loggedUsername;

    private String photoTemp = "default.gif";

    public void save() {
        
        if (uploadedPhoto != null) {
            InscritHelper.copyFileAndRename(inscrit, uploadedPhoto, inputStreamPhoto, uploadFolder);
        }else{
              FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo Personnelle est obligatoire", null));
            return;
        }

        inscritService.save(inscrit);

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre Inscription à été faite avec succès!", null));

    }

   

    public void loadLists() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();
        etapes = inscritService.loadEtapes();
        InscritHelper.loadListCivSex(civilites, sex);
        
       

//       loggedUsername = UserHelper.getLoggedUsername();
//        System.out.println(loggedUsername);
    }

    public void uploadPhoto(FileUploadEvent event) {

        try {
            uploadedPhoto = event.getFile();
            inputStreamPhoto = uploadedPhoto.getInputstream();
            photoContentsAsBase64 = Base64.getEncoder().encodeToString(uploadedPhoto.getContents());
        } catch (IOException ex) {
            Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void transferFile(Part uploadedFile, String folder) {

        InputStream input = null;
        try {
            input = uploadedFile.getInputStream();
            {
                String inputName = uploadedFile.getSubmittedFileName();
                Files.copy(input, new File(folder, inputName).toPath(), StandardCopyOption.REPLACE_EXISTING);

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

    public void loadLoggedInscrit() {
        LoggedUser user = UserHelper.getLoggedUser();
        if (user != null) {
            Optional<Inscrit> optional = inscritService.getInscrit(user.getUsername());
            if (optional.isPresent()) {
                try {
                    inscrit = optional.get();
                    byte[] contents = Files.readAllBytes(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
                    photoContentsAsBase64 = Base64.getEncoder().encodeToString(contents);
                } catch (IOException ex) {
                    Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                inscrit = new Inscrit();
                inscrit.setCne(user.getUsername());
                inscrit.setCin(user.getPassword());
                photoContentsAsBase64 = null;
            }
        }
    }

    public void initialize() {
        loadLoggedInscrit();
        loadLists();

    }

}
