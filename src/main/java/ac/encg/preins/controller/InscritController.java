package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Bac;
import ac.encg.preins.entity.Etape;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.helper.FilesHelper;
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
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import ac.encg.preins.nonPersistable.Civility;
import ac.encg.preins.nonPersistable.LoggedUser;
import ac.encg.preins.nonPersistable.Mention;
import ac.encg.preins.nonPersistable.Sex;
import ac.encg.preins.service.UserService;
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

    @Autowired
    private UserService userService;

    private String tempFolder = "D:\\PreinsTemp\\";
    private String uploadFolder = "D:\\PreinsUploads\\";
    private UploadedFile uploadedPhoto;
    private UploadedFile uploadedCin;
    private InputStream inputStreamPhoto;
    private String photoContentsAsBase64;

    private Inscrit inscrit = new Inscrit();
    private List<Inscrit> inscrits = new ArrayList<>();
    private List<Inscrit> filteredInscrits;
    
    private List<Etape> etapes = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();
    private List<Civility> civilities = new ArrayList<>();
    private List<Sex> sexes = new ArrayList<>();
    private List<Mention> mentions = new ArrayList<>();

    private String selectedCne;

    private String loggedUsername;

    private String photoTemp = "default.gif";

    public void save() throws IOException {

        if (photoContentsAsBase64 != null) {
     //       InscritHelper.copyFileAndRename(inscrit, uploadedPhoto, inputStreamPhoto, uploadFolder);
            FilesHelper.savePhoto(photoContentsAsBase64, uploadFolder+inscrit.getCin() + FilesHelper.getFileExtension(uploadedPhoto.getFileName()));
        } else{
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
        civilities = InscritHelper.loadListCivilities();
        sexes = InscritHelper.loadListSexes();
        mentions = InscritHelper.loadListMentions();

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

    public void loadInscrit() {

        if (UserHelper.isRoleUser(userService.getAuthentication())) {
            LoggedUser user = UserHelper.getLoggedUser(userService.getAuthentication());
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
        } else {
            if (selectedCne != null) {
                Optional<Inscrit> optional = inscritService.getInscrit(selectedCne);
                if (optional.isPresent()) {
                    try {
                        inscrit = optional.get();
                        byte[] contents = Files.readAllBytes(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
                        photoContentsAsBase64 = Base64.getEncoder().encodeToString(contents);
                    } catch (IOException ex) {
                        Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    public void validateInscrition() {
        inscrit.setVALID(true);
         inscritService.save(inscrit);
         FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "L'inscription à été Validée avec succès!", null));
        
    }
    public void inValidateInscrition() {
        inscrit.setVALID(false);
         inscritService.save(inscrit);
         FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "L'inscription à été invalidée avec succès!", null));
        
    }

    public void initialize() {
        loadInscrit();
        loadLists();

    }

    public void loadListInscrit() {
        inscrits = inscritService.loadAll();
       
    }

    public void injectData(){
        List<Inscrit> inscriptions = new ArrayList<>();
        SerieBac serieBac =new SerieBac();
        Academie academie = inscritService.loadAcademie();
        Province prov = inscritService.loadProvince();
        serieBac = inscritService.loadSerie();
        Etape etape = inscritService.loadEtape();
        Pays pays = inscritService.loadPay();
        for (int i = 1 ; i<100;i = i+1){
            Inscrit ins= new Inscrit();
            ins.setAdresse("Adresse"+i);
            ins.setAdresseTuteur("AdresseTut"+i);
            ins.setCin("CIN"+i);
            ins.setCinTuteur("CINTut"+i);
            ins.setCiv(new Byte("1"));
            ins.setCne("CNE"+i);
            ins.setEmail("Email"+i);
            ins.setLieuNaiss("Lieu"+i);
            ins.setMentionBac("TB");
            ins.setNom("Nom"+i);
            ins.setPrenom("Prenom"+i);
            ins.setVALID(false);
            
       
            Bac bac = new Bac();
            bac.setSerieBac(serieBac);
            bac.setAcademie(academie);
            bac.setProvinceBac(prov);
            ins.setBac(bac);
            ins.setEtape(etape);
            ins.setPaysNaiss(pays);
            ins.setProvinceNaiss(prov);
            
             inscriptions.add(ins);
             System.out.println("Inscrit"+i+"Injecté");
        };
        
        inscritService.saveAll(inscriptions);
    }

}
