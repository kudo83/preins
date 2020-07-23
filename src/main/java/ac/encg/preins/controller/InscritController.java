package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Admis;
import ac.encg.preins.entity.Etape;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pcs;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.entity.User;
import ac.encg.preins.helper.CommonHelper;
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
import javax.inject.Named;
import javax.faces.context.FacesContext;

import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import ac.encg.preins.nonPersistable.Civility;
import ac.encg.preins.nonPersistable.LoggedUser;
import ac.encg.preins.nonPersistable.Mention;
import ac.encg.preins.nonPersistable.Sex;
import ac.encg.preins.service.AdmisService;
import ac.encg.preins.service.UserService;
import ac.encg.preins.utility.SendMail;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.DataOutputStream;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@Scope("session")
@Named("inscritController")
public class InscritController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private InscritService inscritService;

    @Autowired
    private AdmisService admisService;

    @Autowired
    private UserService userService;

    //Local
//    private String uploadFolder = "D:\\PreinsUploads\\";


    //Server
    private String uploadFolder = "/opt/apache-tomcat-9.0.20/webapps/PreinsUploads/";


    private UploadedFile uploadedPhoto;
    //  private UploadedFile uploadedCin;
    private InputStream inputStreamPhoto;
    private String photoContentsAsBase64;

    private boolean disabledField;

    private Inscrit inscrit = new Inscrit();
    private List<Inscrit> inscrits = new ArrayList<>();
    private Iterable<Inscrit> inscritsValid = new ArrayList<>();
    private List<Inscrit> filteredInscrits;

    private List<Etape> etapes = new ArrayList<>();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();
    private List<Civility> civilities = new ArrayList<>();
    private List<Pcs> pcsList = new ArrayList<>();
    private List<Sex> sexes = new ArrayList<>();
    private List<Mention> mentions = new ArrayList<>();

    private String selectedCne;

    private User connectedUser;

    private String loggedUsername;

    private String photoTemp = "default.gif";

    public static final Font COURRIER_BOLD_18 = new Font(FontFamily.COURIER, 18, Font.BOLD);
    public static final Font COURRIER_NORMAL_18 = new Font(FontFamily.COURIER, 18, Font.NORMAL);
    public static final Font COURRIER_BOLD_16 = new Font(FontFamily.COURIER, 16, Font.BOLD);
    public static final Font COURRIER_NORMAL_16 = new Font(FontFamily.COURIER, 16, Font.NORMAL);
    public static final Font COURRIER_BOLD_14 = new Font(FontFamily.COURIER, 14, Font.BOLD);
    public static final Font COURRIER_NORMAL_14 = new Font(FontFamily.COURIER, 14, Font.NORMAL);
    public static final Font COURRIER_BOLD_12 = new Font(FontFamily.COURIER, 12, Font.BOLD);
    public static final Font COURRIER_NORMAL_12 = new Font(FontFamily.COURIER, 12, Font.NORMAL);

    public void save() throws IOException {

        Iterable<Admis> admisIt = admisService.findByCneOrCin(inscrit.getCne(), inscrit.getCin());
        int counter = 0;
        for (Admis i : admisIt) {
            counter++;
        }
        if (counter > 1) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Le code massa saisie et le CIN correspendent à différent admis. Veuillez bien vérifier votre saisie et contacter l'administration SI vos donnée sont correctes!"));
            return;
        } else if (counter == 0) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Vous ne figurez pas sur la liste des Admis! Veuillez contacter l'administration!"));
            return;
        } else if (counter == 1) {

            Admis admis = admisIt.iterator().next();
            if (admis.getUser() == null) {
                connectedUser.setAdmis(admis);
            } else {
                if (!connectedUser.getId().equals(admis.getUser().getId())) {
                    FacesContext.getCurrentInstance().
                            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Un condidat est déjà inscrit avec ce Code Massar ou CIN! Veuillez contacter l'administration!"));
                    return;
                }
            }

        }

        List<Inscrit> inscritList = inscritService.findByCneOrCin(inscrit.getCne(), inscrit.getCin());

        if (inscrit.getId() == null && !inscritList.isEmpty()) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Un condidat est déjà inscrit avec ce Code Massar ou CIN! Veuillez contacter l'administration!"));
            return;
        }

        if (photoContentsAsBase64 != null) {
            if (uploadedPhoto != null) {
                // String extension = FilesHelper.getFileExtension(uploadedPhoto.getFileName());
                FilesHelper.savePhoto(photoContentsAsBase64, uploadFolder + inscrit.getCin().toUpperCase() + ".jpg");
//                FilesHelper.saveInputStreamPhoto(uploadedPhoto.getInputStream(), uploadFolder + inscrit.getCin() + extension, extension);

//                inscrit.setPhotoFileName(inscrit.getCin() + FilesHelper.getFileExtension(uploadedPhoto.getFileName()));
                 inscrit.setPhotoFileName(inscrit.getCin().toUpperCase() + ".jpg");
            }
        } else {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le champ Photo Personnelle est obligatoire"));
            return;

        }

       
        inscrit = InscritHelper.toUpperCaseInscrit(inscrit);
        Date date = new java.util.Date();
        if (inscrit.getId() == null) {
            inscrit.setDateCreat(new Timestamp(date.getTime()));
            //    inscritService.save(inscrit);
            connectedUser.setInscrit(inscrit);
            connectedUser.setNom(inscrit.getNom() + " " + inscrit.getPrenom());
            connectedUser = userService.save(connectedUser);
            inscrit = connectedUser.getInscrit();
            SendMail.sendInscriptionConfirmationMail(connectedUser.getUsername(), inscrit.getId());
            disabledField = true;

        } else {
            inscrit.setDateModif(new Timestamp(date.getTime()));
            inscrit.setUserModif(connectedUser.getNom());
            //       inscritService.save(inscrit);
            connectedUser.setInscrit(inscrit);
            userService.save(connectedUser);
        }
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Votre Inscription a été enregistré avec succès!"));

    }

    public void loadLists() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();
        etapes = inscritService.loadEtapes();
        pcsList = inscritService.loadPcsList();
        civilities = InscritHelper.loadListCivilities();
        sexes = InscritHelper.loadListSexes();
        mentions = InscritHelper.loadListMentions();

//       loggedUsername = UserHelper.getLoggedUsername();
//        System.out.println(loggedUsername);
    }

    public void handleUploadPhoto(FileUploadEvent event) {

        try {
            uploadedPhoto = event.getFile();
            inputStreamPhoto = uploadedPhoto.getInputStream();
            photoContentsAsBase64 = Base64.getEncoder().encodeToString(uploadedPhoto.getContent());

        } catch (IOException ex) {
            Logger.getLogger(InscritController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InscritController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();

            } catch (IOException ex) {
                Logger.getLogger(InscritController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadInscrit() {

        if (UserHelper.isRoleUser(userService.getAuthentication())) {

            inscrit = connectedUser.getInscrit();

            if (inscrit != null) {
                try {

                    byte[] contents = Files.readAllBytes(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
                    photoContentsAsBase64 = Base64.getEncoder().encodeToString(contents);
                    disabledField = true;

                } catch (IOException ex) {
                    Logger.getLogger(InscritController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                inscrit = new Inscrit();
                photoContentsAsBase64 = null;
                disabledField = false;
            }

        } else {
            if (selectedCne != null) {
                Optional<Inscrit> optionalIns = inscritService.getInscritByCne(selectedCne);
                if (optionalIns.isPresent()) {
                    try {
                        inscrit = optionalIns.get();
                        byte[] contents = Files.readAllBytes(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
                        photoContentsAsBase64 = Base64.getEncoder().encodeToString(contents);
                        disabledField = true;

                    } catch (IOException ex) {
                        Logger.getLogger(InscritController.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }
    }

    public void validateInscrition() throws IOException {

        inscrit.setVALID(true);

        Date date = new Date();
        inscrit.setDateValid(new Timestamp(date.getTime()));
        inscrit.setUserValid(connectedUser.getNom());

        inscritService.save(inscrit);
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "L'inscription à été Validée avec succès!"));
        //   FacesContext.getCurrentInstance().getExternalContext().redirect("inscritList.xhtml");

    }

    public void inValidateInscrition() throws IOException {
        inscrit.setVALID(false);
        Date date = new Date();
        inscrit.setDateInvalid(new Timestamp(date.getTime()));
        inscrit.setUserInValid(connectedUser.getNom());
        inscritService.save(inscrit);

        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "L'inscription à été invalidée avec succès!"));
        //  FacesContext.getCurrentInstance().getExternalContext().redirect("inscritList.xhtml");

    }

    public void initialize() {
        loadInscrit();
        loadLists();

    }

    public void loadListInscrit() {
        inscrits = inscritService.loadAll();

    }

    public void loadListInscritValid() {
        inscritsValid = inscritService.loadAllValid();

    }

    public void createReceipeForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DocumentException, URISyntaxException {
        loadInscrit();
        Document document = new Document();
        FacesContext context = FacesContext.getCurrentInstance();
        response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"Reçu_Pré-ins_ENCGA_" + inscrit.getCne() + ".pdf\"");
        PdfWriter.getInstance(document, new DataOutputStream(response.getOutputStream()))
                .setInitialLeading(16);

        Chunk tab = new Chunk(new VerticalPositionMark(), 160, true);

        document.open();
//        document.add(new Paragraph("Hello word"));
//        document.close();
        String relativeWebPath = "resources/spark-layout/images/headerPdf.jpg";
        ServletContext servletContext = request.getServletContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        //  Path path = Paths.get(ClassLoader.getSystemResource("images/logo.jpg").toURI());
        Image logo = Image.getInstance(absoluteDiskPath);
        logo.setAlignment(Image.ALIGN_CENTER);
        logo.scalePercent(50);
        logo.setSpacingAfter(100);
        document.add(logo);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Paragraph titre = new Paragraph("Reçu de pré-inscription \n \n", COURRIER_BOLD_16);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        document.add(new Chunk(tab));
        document.add(new Chunk("Référence   :    ", COURRIER_BOLD_14));
        document.add(new Chunk("Pré-2020-" + inscrit.getId().toString(), COURRIER_NORMAL_14));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk(tab));
        document.add(new Chunk("Nom         :    ", COURRIER_BOLD_14));
        document.add(new Chunk(inscrit.getNom(), COURRIER_NORMAL_14));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk(tab));
        document.add(new Chunk("Prénom      :    ", COURRIER_BOLD_14));
        document.add(new Chunk(inscrit.getPrenom(), COURRIER_NORMAL_14));
        document.add(Chunk.NEWLINE);

        document.add(new Chunk(tab));
        document.add(new Chunk("Code Massar :    ", COURRIER_BOLD_14));
        document.add(new Chunk(inscrit.getCne(), COURRIER_NORMAL_14));
        document.add(Chunk.NEWLINE);

        Image photo = Image.getInstance(uploadFolder + inscrit.getPhotoFileName());
        photo.scaleAbsolute(105, 105);
        document.add(new Chunk(photo, 25, 0, false));

        document.add(new Chunk(tab));
        document.add(new Chunk("CIN         :    ", COURRIER_BOLD_14));
        document.add(new Chunk(inscrit.getCin(), COURRIER_NORMAL_14));

        document.add(Chunk.NEWLINE);

        document.add(new Chunk(tab));
        document.add(new Chunk("Niveau      :    ", COURRIER_BOLD_14));
        document.add(new Chunk(inscrit.getEtape().getLib(), COURRIER_NORMAL_14));

        document.close();

//        servletOutputStream.flush();
//        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void createReceipeForOperator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DocumentException, URISyntaxException {

        //          Document document =              new Document(PageSize.A4, 50, 50, 50, 50);
        Document document = new Document();
        FacesContext context = FacesContext.getCurrentInstance();
        response = (HttpServletResponse) context.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=\"" + inscrit.getNom() + " " + inscrit.getPrenom() + "-Reçu_Dépo.pdf\"");
        PdfWriter.getInstance(document, new DataOutputStream(response.getOutputStream()))
                .setInitialLeading(16);

        Chunk tab = new Chunk(new VerticalPositionMark(), 50, true);

        document.open();
//        document.add(new Paragraph("Hello word"));
//        document.close();
        String relativeWebPath = "resources/spark-layout/images/headerPdf.jpg";
        ServletContext servletContext = request.getServletContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        //  Path path = Paths.get(ClassLoader.getSystemResource("images/logo.jpg").toURI());
        Image logo = Image.getInstance(absoluteDiskPath);
        logo.setAlignment(Image.ALIGN_CENTER);
        logo.scalePercent(50);
        logo.setSpacingAfter(100);
        document.add(logo);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Paragraph titre = new Paragraph("CERTIFICAT DE DEPOT DE DOSSIER \n \n", COURRIER_BOLD_18);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new Chunk("L'adminstration de l'Ecole Nationale de Commerce et de Gestion d'Agadir, certifie que l'étudiant(e) ", COURRIER_NORMAL_14));
        document.add(new Chunk(inscrit.getNom() + " " + inscrit.getPrenom(), COURRIER_BOLD_14));

        document.add(Chunk.NEWLINE);
        document.add(new Chunk("a déposé un dossier pour l'inscription en ", COURRIER_NORMAL_14));
        document.add(new Chunk(inscrit.getEtape().getLib(), COURRIER_BOLD_14));

        //   document.add(Chunk.NEWLINE);
        document.add(new Chunk(" sous le numéro :", COURRIER_NORMAL_14));
        document.add(new Chunk("Pré-2020-" + inscrit.getId().toString(), COURRIER_BOLD_14));

        document.add(Chunk.NEWLINE);
        document.add(new Chunk("au titre de l'année universitaire : ", COURRIER_NORMAL_14));
        document.add(new Chunk("2020 / 2021", COURRIER_BOLD_14));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new Chunk(new VerticalPositionMark(), 300, true));
        document.add(new Chunk("Date : ", COURRIER_BOLD_14));
        document.add(new Chunk(CommonHelper.formateDate(LocalDate.now()), COURRIER_NORMAL_14));

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        document.add(new Chunk("NB: L'étudiant(e) ne sera considéré(e) définitivement inscrit(e) que lorsqu'il lui sera délivré"
                + " l'Attestation d'inscription.", COURRIER_NORMAL_12));

        document.setMargins(100, 100, 0, 0);
        document.close();

//        servletOutputStream.flush();
//        servletOutputStream.close();
        FacesContext.getCurrentInstance().responseComplete();

    }

    @PostConstruct
    public void loadConnectedUser() throws IOException {
        LoggedUser loggedUser = UserHelper.getLoggedUser(userService.getAuthentication());
        if (loggedUser != null) {
            Optional<User> optional = userService.getUser(loggedUser.getUsername());
            if (optional.isPresent()) {
                connectedUser = optional.get();
            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez vous reconnecter!"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }

        }
    }

}
