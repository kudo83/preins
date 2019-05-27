/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import ac.encg.preins.entity.Inscrit;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kudo
 */
public class InscritHelper {

    public static void copyFileAndRename(Inscrit inscrit, UploadedFile uploadedPhoto, InputStream inputStreamPhoto, String uploadFolder){
        String photoFileName = inscrit.getCne()  + FilesHelper.getFileExtension(uploadedPhoto.getFileName());
        try {
            //Delete older file
            Files.delete(Paths.get(uploadFolder+inscrit.getPhotoFileName()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        FilesHelper.copyFile(photoFileName, inputStreamPhoto, uploadFolder);
        inscrit.setPhotoFileName(photoFileName);
    }

}
