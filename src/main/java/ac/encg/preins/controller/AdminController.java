package ac.encg.preins.controller;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.entity.User;
import ac.encg.preins.helper.CommonHelper;
import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.nonPersistable.LoggedUser;
import ac.encg.preins.service.AdmisService;
import ac.encg.preins.service.StatService;
import ac.encg.preins.service.UserService;
import java.io.IOException;
import java.io.Serializable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Aissam
 */
@Controller
@Getter
@Setter
@ViewScoped
@Named("adminController")
public class AdminController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AdmisService admisService;

    @Autowired
    private UserService userService;

    @Autowired
    private StatService statService;

    private User connectedUser;

    private int nombreDuplique = 0;
    private int nombreInsérer = 0;

    private UploadedFile excelUpload;

    private List<Admis> admisList = new ArrayList<>();

    private long nbrAdmis = 0;
    private long nbrInscrits = 0;
    private long nbrUsers = 0;

    private String cneAdmis;
    private String cinAdmis;
    private String nomAdmis;

    private List<Admis> filteredAdmisList;

    /**
     * Uploader fichier excel des admis
     *
     * @param event
     */
    public void uploadAdmis(FileUploadEvent event) {

        Workbook workbook = null;
        try {
            //  workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
            workbook = Workbook.getWorkbook(event.getFile().getInputStream());

            Sheet sheet = workbook.getSheet(0);
            int cellCount = sheet.getColumn(2).length;
            boolean hasValue = true;

            for (int i = 0; i < cellCount; i++) {
                Cell cell1 = sheet.getCell(0, i);
                Cell cell2 = sheet.getCell(1, i);
                Cell cell3 = sheet.getCell(2, i);

                if ((!CommonHelper.isNullOrEmpty(cell1.getContents()) && admisService.existByCne(cell1.getContents()))
                        || (!CommonHelper.isNullOrEmpty(cell2.getContents()) && admisService.existByCin(cell2.getContents()))) {
                    nombreDuplique++;
                } else {

                    Admis newAdmis = new Admis();

                    newAdmis.setCne(cell1.getContents().toUpperCase());
                    newAdmis.setCin(cell2.getContents().toUpperCase());
                    newAdmis.setNom(cell3.getContents().toUpperCase());

                    Date date = new Date();
                    newAdmis.setDateCREAT(new Timestamp(date.getTime()));
                    newAdmis.setUserCreat(connectedUser.getNom());

                    admisList.add(newAdmis);
                    nombreInsérer++;
                }
            }
            if (!admisList.isEmpty()) {
                admisService.saveAdmisList(admisList);

            }
            if (nombreInsérer > 0) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                String.valueOf(nombreInsérer) + " admis insérés", null));
            }
            if (nombreDuplique > 0) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                String.valueOf(nombreDuplique) + " dupliquations rejetés", null));
            }

            nombreInsérer = 0;
            nombreDuplique = 0;
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Un ou plusieur étudiants existe déjà dans la base!", null));
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }

    }

    public void sendConfirmationMail() {
        //    SendMail.sendConfirmationMail("ai.izimi@gmail.com");
    }

    public void initStatistics() {
        nbrAdmis = statService.countAdmis();
        nbrInscrits = statService.countInscrits();
        nbrUsers = statService.countUsers();
    }

    public void loadListAdmis() {
        admisList = admisService.loadAll();

    }

    public void modifierAdmis(RowEditEvent<Admis> event) {
        Optional<Admis> optionalAdmis;
        if (!CommonHelper.isNullOrEmpty(event.getObject().getCne())) {
            optionalAdmis = admisService.findByCne(event.getObject().getCne());
            if (optionalAdmis.isPresent() && (event.getObject().getId() != optionalAdmis.get().getId())) {
                FacesContext.getCurrentInstance().
                        addMessage("Erreur:", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le CNE saisie existe déjà pour un autre admis!", null));
                return;
            }
        }

        if (!CommonHelper.isNullOrEmpty(event.getObject().getCin())) {
            optionalAdmis = admisService.findByCin(event.getObject().getCin());
            if (optionalAdmis.isPresent() && (event.getObject().getId() != optionalAdmis.get().getId())) {
                FacesContext.getCurrentInstance().
                        addMessage("Erreur:", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le CIN saisie existe déjà pour un autre admis!", null));
                return;
            }
        }

//        Admis admis = event.getObject();
//        admis.setCne(event.getObject().getCne());
//        admis.setCin(event.getObject().getCin());
//        admis.setNom(event.getObject().getNom());
//        admis.setDateModif(new Date(System.currentTimeMillis()));
//        admis.setUserModif(connectedUser);
//        admisService.save(admis);
//        FacesContext.getCurrentInstance().
//                addMessage("Info:", new FacesMessage(FacesMessage.SEVERITY_INFO, "L'admis a été ajouté avec succée!", null));
//        admisList = admisService.loadAll();
//        cneAdmis = "";
//        cinAdmis = "";
//        nomAdmis = "";
        admisService.saveAdmisList(admisList);
        FacesMessage msg = new FacesMessage("Admis modifié", event.getObject().getNom());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

//    public void validCne(RowEditEvent<Admis> event) {
//       if (!CommonHelper.isNullOrEmpty(event.getObject().getCne())) {
//           Optional<Admis> optionalAdmis = admisService.findByCne(event.getObject().getCne());
//            if (optionalAdmis.isPresent() && (event.getObject().getId() != optionalAdmis.get().getId())) {
//                FacesContext.getCurrentInstance().
//                        addMessage("Erreur:", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le CNE saisie existe déjà pour un autre admis!", null));
//            }
//        }
//    }
//    
//     public void onCellEdit(CellEditEvent event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//         
//        if(newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
//
//    public void modifCancel(RowEditEvent<Admis> event) {
//        FacesMessage msg = new FacesMessage("Modification annulée", event.getObject().getNom());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public void ajouterAdmis() throws IOException {
        boolean noteValid = false;
        if (CommonHelper.isNullOrEmpty(nomAdmis)) {
            noteValid = true;
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez entrer le nom et le prénom du condidat"));
        }
        if (CommonHelper.isNullOrEmpty(cinAdmis) && CommonHelper.isNullOrEmpty(cneAdmis)) {
            noteValid = true;
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner le CNE ou le CIN du condidat"));
        }
        if (noteValid) {
            return;
        }
        if (!CommonHelper.isNullOrEmpty(cneAdmis) && admisService.existByCne(cneAdmis)) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le CNE saisie existe déjà dans la base"));
        } else if (!CommonHelper.isNullOrEmpty(cinAdmis) && admisService.existByCin(cinAdmis)) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le CIN saisie existe déjà dans la base"));

        } else {
            Admis newAdmis = new Admis();
            newAdmis.setCne(cneAdmis.toUpperCase());
            newAdmis.setCin(cinAdmis.toUpperCase());
            newAdmis.setNom(nomAdmis.toUpperCase());

            Date date = new Date();
            newAdmis.setDateCREAT(new Timestamp(date.getTime()));
            newAdmis.setUserCreat(connectedUser.getNom());
            admisService.save(newAdmis);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "L'admis a été ajouté avec succée!", null));
            admisList = admisService.loadAll();
            cneAdmis = "";
            cinAdmis = "";
            nomAdmis = "";

        }
    }

    public void loadConnectedUser() throws IOException {
        LoggedUser loggedUser = UserHelper.getLoggedUser(userService.getAuthentication());
        if (loggedUser != null) {
            Optional<User> optional = userService.getUser(loggedUser.getUsername());
            if (optional.isPresent()) {
                connectedUser = optional.get();
            } else {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Veuillez vous reconnecter!", null));
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }

        }
    }

}
