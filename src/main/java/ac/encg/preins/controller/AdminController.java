package ac.encg.preins.controller;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.entity.User;
import ac.encg.preins.helper.CommonHelper;
import ac.encg.preins.helper.UserHelper;
import ac.encg.preins.nonPersistable.LoggedUser;
import ac.encg.preins.service.AdmisService;
import ac.encg.preins.service.InscritService;
import ac.encg.preins.service.StatService;
import ac.encg.preins.service.UserService;
import java.io.IOException;
import java.io.Serializable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
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
@Named("adminController")
public class AdminController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AdmisService admisService;

    @Autowired
    private UserService userService;

    @Autowired
    private InscritService inscritService;

    @Autowired
    private StatService statService;

    private User connectedUser;

    private int nombreDuplique = 0;
    private int nombreInsérer = 0;

    private BarChartModel barModelNbrInscritValide;

    private UploadedFile excelUpload;

    private List<Admis> admisList = new ArrayList<>();

    private long nbrAdmis = 0;
    private long nbrInscrits = 0;
    private long nbrUsers = 0;
    private long nbrInscritValid = 0;

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
        List<Admis> admisListNew = new ArrayList<Admis>();
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
                } else if (!CommonHelper.isNullOrEmpty(cell3.getContents())) {

                    Admis newAdmis = new Admis();

                    newAdmis.setCne(cell1.getContents().toUpperCase());
                    newAdmis.setCin(cell2.getContents().toUpperCase());
                    newAdmis.setNom(cell3.getContents().toUpperCase());

                    Date date = new Date();
                    newAdmis.setDateCREAT(new Timestamp(date.getTime()));
                    newAdmis.setUserCreat(connectedUser.getNom());

                    admisListNew.add(newAdmis);
                    nombreInsérer++;
                }
            }
            if (!admisListNew.isEmpty()) {
                admisService.saveAdmisList(admisListNew);

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
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Une erreur interne s'est produite.Veuillez contacter l'admin"));
            e.printStackTrace();
        } finally {
            loadListAdmis();
            nbrAdmis = admisList.size();
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
        nbrInscritValid = statService.countInscritValid();
    }

    public void createBarChartModel() {
        barModelNbrInscritValide = new BarChartModel();

        Iterable<User> operateurs = userService.findOperateurs();

        ChartSeries insrits = new ChartSeries();
        insrits.setLabel("Inscrits Validés");

        for (User u : operateurs) {
            insrits.set(u.getNom(), inscritService.countInscritValidByOperator(u.getNom()));
        }

        barModelNbrInscritValide.addSeries(insrits);

//        barModelNbrInscritValide.setTitle("Inscrits Validés par Opérateur");
        barModelNbrInscritValide.setLegendPosition("ne");

        Axis xAxis = barModelNbrInscritValide.getAxis(AxisType.X);
//        xAxis.setLabel("Opérateurs");

        Axis yAxis = barModelNbrInscritValide.getAxis(AxisType.Y);
//        yAxis.setLabel("Inscrits");
        yAxis.setMin(0);
        yAxis.setTickCount(1);
        //  yAxis.setMax(200);
        barModelNbrInscritValide.setExtender("skinBar");

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
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le CNE saisie existe déjà pour un autre admis"));
                return;
            }
        }

        if (!CommonHelper.isNullOrEmpty(event.getObject().getCin())) {
            optionalAdmis = admisService.findByCin(event.getObject().getCin());
            if (optionalAdmis.isPresent() && (event.getObject().getId() != optionalAdmis.get().getId())) {
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur!" , "Le CIN saisie existe déjà pour un autre admis"));
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
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "L'admis a été ajouté avec succée!"));
            admisList = admisService.loadAll();
            nbrAdmis++;
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
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez vous reconnecter!"));
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            }

        }
    }

}
