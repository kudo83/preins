package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.service.InscritService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
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
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();

    private String loggedUsername;
    
    private String photoTemp = "default.gif";

    public void save() {
        if (photosObligatoires()) {
            return;
        }

        copyFileAndRename();

        inscritService.save(inscrit);

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre Inscription à été faite avec succès!", null));

    }

    private void copyFileAndRename() {
        String photoFileName = inscrit.getCne()  + getFileExtension(uploadedPhoto.getFileName());
        copyFile(photoFileName, inputStreamPhoto, uploadFolder);//        Files.copy(Paths.get(tempFolder + fileName), Paths.get(uploadFolder + photoFileName), StandardCopyOption.REPLACE_EXISTING);
//        Files.delete(Paths.get(tempFolder + fileName));
    }

    public boolean photosObligatoires() {
        boolean retour = false;
        if (inscrit.getPhotoFileName() == null) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo Personnelle est obligatoire", null));
            retour = true;
        }

        return retour;
    }

    public void loadLists() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();

//       loggedUsername = UserHelper.getLoggedUsername();
//        System.out.println(loggedUsername);
    }

    public void uploadPhoto(FileUploadEvent event) {

        try {
            uploadedPhoto = event.getFile();
            inputStreamPhoto = uploadedPhoto.getInputstream();
            photoContentsAsBase64 = Base64.getEncoder().encodeToString( uploadedPhoto.getContents());
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

    public String getFileExtension(String fileName) {
        String extension = "";
        try {

            extension = fileName.substring(fileName.lastIndexOf("."));

        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    public void loadLoggedInscrit() {
        String username = UserHelper.getLoggedUsername();
        if (username != null) {
            Optional<Inscrit> optional = inscritService.getInscrit(username);
            if (optional.isPresent()) {
                try {
                    inscrit = optional.get();
                    byte[] contents = Files.readAllBytes(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
                    photoContentsAsBase64 = Base64.getEncoder().encodeToString(contents);
                } catch (IOException ex) {
                    Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{
                inscrit = new Inscrit();
                photoContentsAsBase64 = null;
            }
        }
    }

    public void copyFile(String fileName, InputStream in, String destination) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

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

    public void initialize() {
        loadLoggedInscrit();
        loadLists();

    }

}
