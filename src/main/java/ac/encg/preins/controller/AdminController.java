package ac.encg.preins.controller;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.service.AdmisService;
import ac.encg.preins.service.StatService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
import org.primefaces.event.FileUploadEvent;
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
    private StatService statService;

    private int nombreDuplique = 0;
    private int nombreInsérer = 0;

    private UploadedFile excelUpload;

    private List<Admis> admisList = new ArrayList<>();

    private long nbrAdmis = 0;
    private long nbrInscrits = 0;
    private long nbrUsers = 0;

    private Admis admis = new Admis();

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
            int cellCount = sheet.getColumn(0).length;
            boolean hasValue = true;

            for (int i = 0; i < cellCount; i++) {
                Cell cell1 = sheet.getCell(0, i);
                Cell cell2 = sheet.getCell(1, i);
                Cell cell3 = sheet.getCell(2, i);

                if (admisService.existByCneOrCin(cell1.getContents(), cell2.getContents())) {
                    nombreDuplique++;
                } else {

                    Admis newAdmis = new Admis();

                    newAdmis.setCne(cell1.getContents());
                    newAdmis.setCin(cell2.getContents());
                    newAdmis.setNom(cell3.getContents());

                    admisList.add(newAdmis);
                    nombreInsérer++;
                }
            }
            if (!admisList.isEmpty()) {
                admisService.saveAdmisList(admisList);

            }
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            String.valueOf(nombreInsérer) + " admis inséré est " + String.valueOf(nombreDuplique) + " dupliquations rejetés", null));
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

}
