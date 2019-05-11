package ac.encg.preins.controller;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FileUploadBean {
    private String fileContent;
    private String fileName;

    public void handleUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        byte[] contents = file.getContents();
        fileContent = new String(contents);
        fileName = file.getFileName();
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }
}