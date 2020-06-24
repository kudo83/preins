package ac.encg.preins.controller;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.service.AdmisService;
import ac.encg.preins.utility.SendMail;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Named("admisController")
public class AdmisController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private AdmisService userService;

    private String EXCEL_FILE_LOCATION = "/opt/apache-tomcat-9.0.20/webapps/accounts/comptes.xls";

    private UploadedFile excelUpload;

    private List<Admis> admisList = new ArrayList<>();

    public void uploadAdmis(FileUploadEvent event) {

        Workbook workbook = null;
        try {
            //  workbook = Workbook.getWorkbook(new File(EXCEL_FILE_LOCATION));
            workbook = Workbook.getWorkbook(event.getFile().getInputStream());

            Sheet sheet = workbook.getSheet(0);
            int cellCount = sheet.getColumn(0).length;
            boolean hasValue = true;

//            Role userRole = new Role();
//            userRole.setId(3);
//            userRole.setLib("USER");
            for (int i = 0; i < cellCount; i++) {
                Cell cell1 = sheet.getCell(0, i);
                Cell cell2 = sheet.getCell(1, i);
                Cell cell3 = sheet.getCell(2, i);
                Admis newAdmis = new Admis();
                newAdmis.setCne(cell1.getContents());
                newAdmis.setCin(cell2.getContents());
                newAdmis.setNom(cell3.getContents());
                admisList.add(newAdmis);
            }
            if (!admisList.isEmpty()) {
                userService.saveAdmisList(admisList);
            }

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

    public void sendEmail() {
        SendMail.send();
    }

    public void sendConfirmationMail() {
        //    SendMail.sendConfirmationMail("ai.izimi@gmail.com");
    }
//
//    public void uploadExcelApache() {
//        try {
//
//            FileInputStream excelFile = new FileInputStream(new File(EXCEL_FILE_LOCATION));
//            Workbook workbook = new XSSFWorkbook(excelFile);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = datatypeSheet.iterator();
//
//            while (iterator.hasNext()) {
//
//                Row currentRow = iterator.next();
//                Iterator<Cell> cellIterator = currentRow.iterator();
//
//                while (cellIterator.hasNext()) {
//
//                    Cell currentCell = cellIterator.next();
//                    //getCellTypeEnum shown as deprecated for version 3.15
//                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
//                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
//                        System.out.print(currentCell.getStringCellValue() + "--");
//                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//                        System.out.print(currentCell.getNumericCellValue() + "--");
//                    }
//
//                }
//                System.out.println();
//
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

}