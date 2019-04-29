package ac.encg.preins.controller;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.service.InscritService;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
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

    //private Part  uploadedFile;
    private UploadedFile image;
    private Part uploadedFile;
    private String folder = "D:\\files";
    private UploadedFile cinFile;
    private String pathPhoto = "file:///D:\\files\\default-male.gif";

    private Inscrit inscrit = new Inscrit();
    private List<Province> provinces = new ArrayList<>();
    private List<Pays> pays = new ArrayList<>();
    private List<SerieBac> seriesBac = new ArrayList<>();
    private List<Academie> academies = new ArrayList<>();
    String filename = "default.gif";
    // private List<String> anneeBac =  Arrays.asList("2010","2011","2012","2014","2015","2016","2017","2018","2019");

    public void save() {
        inscritService.save(inscrit);

    }

    public void validate() {

    }

    @PostConstruct
    public void loadProvinces() {
        provinces = inscritService.loadProvinces();
        pays = inscritService.loadPays();
        seriesBac = inscritService.loadBacs();
        academies = inscritService.loadAcademies();
        pathPhoto = "file:///D:\\files\\default-male.gif";

    }

//    public StreamedContent getImage() throws IOException {
////        FacesContext context = FacesContext.getCurrentInstance();
////
////        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
////            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
////            return new DefaultStreamedContent();
////        } else {
////            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
//////            String filename = context.getExternalContext().getRequestParameterMap().get("filename");
////            return new DefaultStreamedContent(new FileInputStream(new File(folder, filename)));
////        }
//        return new DefaultStreamedContent(new FileInputStream(new File(folder, filename)));
//    }

    public void upload() {
        try (InputStream input = uploadedFile.getInputStream()) {
            String inputName = uploadedFile.getSubmittedFileName();

            Files.copy(input, new File(folder, inputName).toPath());
            pathPhoto = "D:\\files\\" + inputName;

            filename = inputName;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void upload() {
//    try {
//        boolean t = true;
//      fileContent = new Scanner(photoFile.getInputstream())
//          .useDelimiter("\\A").next();
//    } catch (IOException e) {
//      // Error handling
//    }
//  }
//    public void fileUpload(FileUploadEvent e) {
//        // Get uploaded file from the FileUploadEvent
//        this.photoFile = e.getFile();
//        // Print out the information of the file
//        System.out.println("Uploaded File Name Is :: " + photoFile.getFileName() + " :: Uploaded File Size :: " + photoFile.getSize());
//        
//        try {
//            copyFileUsingStream(photoFile);
//        } catch (IOException ex) {
//            Logger.getLogger(InscritController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void upload(FileUploadEvent event) {
//        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//        // Do what you want with the file
//        try {
//            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// 
//    }
//    public void copyFile(String fileName, InputStream in) {
//        try {
// 
//            // write the inputStream to a FileOutputStream
//            OutputStream out = new FileOutputStream(new File("" + fileName));
// 
//            int read = 0;
//            byte[] bytes = new byte[2024];
// 
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
// 
//            in.close();
//            out.flush();
//            out.close();
// 
//            System.out.println("New file created!");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private static void copyFileUsingStream(UploadedFile source) throws IOException {
//    File dest =  new File("D:/"+source.getFileName());
//    InputStream is = null;
//    OutputStream os = null;
//    try {
//        is = source.getInputstream();
//        os = new FileOutputStream(dest);
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = is.read(buffer)) > 0) {
//            os.write(buffer, 0, length);
//        }
//    } finally {
//        is.close();
//        os.close();
//    }
//}
}
