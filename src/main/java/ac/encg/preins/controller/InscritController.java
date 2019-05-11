package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
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
public class InscritController implements Serializable {

    @Autowired
    private InscritService inscritService;

    private Part uploadedPhotoFile;
    private Part uploadedCinFile;
    private String tempFolder = "D:\\PreinsTemp\\";
    private String uploadFolder = "D:\\PreinsUploads\\";
    private UploadedFile uploadedFile;

    private Inscrit inscrit = new Inscrit();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();

    private String loggedUsername;
    private String photoTemp = "default.gif";
    private String cinTemp = "id.png";
    private String fileContent;
    private String fileName;

    private byte[] fileContents;
    private String imageContentsAsBase64;
    private byte[] contents;

       
    public void preview(FileUploadEvent event) {
        uploadedFile = event.getFile();
       contents = uploadedFile.getContents();
        fileContent = new String(contents);
        fileName = uploadedFile.getFileName();
        imageContentsAsBase64 = Base64.getEncoder().encodeToString(contents);

    }

    public void save() {
        if (photosNotValid()) {
            return;
        }

        try {
            copyFileAndRename(inscrit.getPhotoFileName(), "P");
            copyFileAndRename(inscrit.getCinFileName(), "C");

        } catch (IOException ex) {
            Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
        }

        inscritService.save(inscrit);

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre Inscription à été faite avec succès!", null));

    }

    private void copyFileAndRename(String fileName, String type) throws IOException {
        String photoFileName = inscrit.getCne() + type + getFileExtension(fileName);
        Files.copy(Paths.get(tempFolder + fileName), Paths.get(uploadFolder + photoFileName), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempFolder + fileName));
    }

    public boolean photosNotValid() {
        boolean retour = false;
        if (inscrit.getPhotoFileName().equals("default.gif")) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo Personnelle est obligatoire", null));
            retour = true;
        }
        if (inscrit.getCinFileName().equals("id.png")) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le champ Photo CIN est obligatoire", null));
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

    public void uploadPhoto() {

//        transferFile(uploadedPhotoFile, tempFolder);
//        photoTemp = uploadedPhotoFile.getSubmittedFileName();
    }

    public void uploadCin() {
        transferFile(uploadedCinFile, tempFolder);
        cinTemp = uploadedCinFile.getSubmittedFileName();

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
                inscrit = optional.get();
                try {
                    Files.copy(Paths.get(uploadFolder + inscrit.getPhotoFileName()), Paths.get(tempFolder + inscrit.getPhotoFileName()), StandardCopyOption.REPLACE_EXISTING);
                    photoTemp = inscrit.getPhotoFileName();
                    Files.copy(Paths.get(uploadFolder + inscrit.getCinFileName()), Paths.get(tempFolder + inscrit.getCinFileName()), StandardCopyOption.REPLACE_EXISTING);
                    cinTemp = inscrit.getCinFileName();
                } catch (IOException ex) {
                    Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
