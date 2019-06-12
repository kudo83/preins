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
import java.util.List;
import ac.encg.preins.nonPersistable.Civility;
import ac.encg.preins.nonPersistable.Mention;
import ac.encg.preins.nonPersistable.Sex;
import com.sun.faces.facelets.util.Path;
import static java.nio.file.Files.copy;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kudo
 */
public class InscritHelper {

    public static void copyFileAndRename(Inscrit inscrit, UploadedFile uploadedPhoto, InputStream inputStreamPhoto, String uploadFolder) throws IOException {
        String photoFileName = inscrit.getCin() + FilesHelper.getFileExtension(uploadedPhoto.getFileName());
//        try {
//            //Delete older file
//            Files.delete(Paths.get(uploadFolder + inscrit.getPhotoFileName()));
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

        Files.copy(inputStreamPhoto, Paths.get(uploadFolder+inscrit.getCin()+FilesHelper.getFileExtension(uploadedPhoto.getFileName())), StandardCopyOption.REPLACE_EXISTING);
//        FilesHelper.copyFile(photoFileName, inputStreamPhoto, uploadFolder);
        inscrit.setPhotoFileName(photoFileName);
    }

    public static List<Civility> loadListCivilities() {
        List<Civility> civilities = new ArrayList<>();
           civilities.add(new Civility(new Byte("1"), "Célibataire"));
            civilities.add(new Civility(new Byte("2"), "Marié(e)"));
        
    return civilities;
    }
    
    public static List<Sex> loadListSexes() {
        List<Sex> sexes = new ArrayList<>();
           sexes.add(new Sex('H', "Masculin"));
            sexes.add(new Sex('F', "Féminin"));
        
    return sexes;
    }
    
    
    public static List<Mention> loadListMentions() {
        List<Mention> mentions = new ArrayList<>();
           mentions.add(new Mention("P", "PASSABLE"));
           mentions.add(new Mention("AB", "ASSEZ BIEN"));
           mentions.add(new Mention("B", "BIEN"));
           mentions.add(new Mention("TB", "TRES BIEN"));
           mentions.add(new Mention("E", "EXCELENT"));
        
    return mentions;
    }
     
    
    
   

}
