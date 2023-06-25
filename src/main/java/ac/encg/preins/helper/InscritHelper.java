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
import ac.encg.preins.nonPersistable.Handicape;
import ac.encg.preins.nonPersistable.Mention;
import ac.encg.preins.nonPersistable.Sex;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.primefaces.model.file.UploadedFile;

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

        Files.copy(inputStreamPhoto, Paths.get(uploadFolder + inscrit.getCin() + FilesHelper.getFileExtension(uploadedPhoto.getFileName())), StandardCopyOption.REPLACE_EXISTING);
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
    public static List<Handicape> loadListHandicapes() {
        List<Handicape> handicapes = new ArrayList<>();
        handicapes.add(new Handicape("A", "Auditif"));
        handicapes.add(new Handicape("AM", "Auditif Moteur"));
        handicapes.add(new Handicape("T", "Auditif Moteur Visuel"));
        handicapes.add(new Handicape("AV", "Auditif Visuel"));
        handicapes.add(new Handicape("XX", "Autre"));
        handicapes.add(new Handicape("M", "Moteur"));
        handicapes.add(new Handicape("MV", "Moteur Visuel"));
        handicapes.add(new Handicape("V", "Visuel"));

        return handicapes;
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

    public static String formatteDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'à' HH:mm:ss z");
       
        return formatter.format(date);
    }
    
    public static Inscrit toUpperCaseInscrit(Inscrit inscrit){
        inscrit.setCin(inscrit.getCin().toUpperCase());
        inscrit.setCne(inscrit.getCne().toUpperCase());
        inscrit.setTypeAdmis(inscrit.getTypeAdmis().toUpperCase());
        inscrit.setNom(inscrit.getNom().toUpperCase());
        inscrit.setPrenom(inscrit.getPrenom().toUpperCase());
        inscrit.setPrenomMere(inscrit.getPrenomMere().toUpperCase());
        inscrit.setPrenomPere(inscrit.getPrenomPere().toUpperCase());
        inscrit.setLieuNaiss(inscrit.getLieuNaiss().toUpperCase());
        inscrit.setPrenomPere(inscrit.getPrenomPere().toUpperCase());
        inscrit.setCinTuteur(inscrit.getCinTuteur().toUpperCase());
        return inscrit;
        
    }

}
