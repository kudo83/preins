/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aissam
 */
@Entity
@Getter
@Setter
@Table(name = "INSCRIT")
public class Inscrit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_INS")
    private Long id;

    @Column(name = "VALIDE")
    private boolean isVALID;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "NOM_AR")
    private String nomAr;

    @Column(name = "PRENOM_AR")
    private String prenomAr;

    @Column(name = "CNE")
    private String cne;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "SEX")
    private char sex;

    @Column(name = "CIV")
    private Byte civ;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "PRENOM_PERE")
    private String prenomPere;

    @Column(name = "PRENOM_MERE")
    private String prenomMere;

    @Column(name = "PRENOM_PERE_AR")
    private String prenomPereAr;

    @Column(name = "PRENOM_MERE_AR")
    private String prenomMereAr;

    @Column(name = "CIN_TUTEUR")
    private String cinTuteur;

    @Column(name = "TEL_TUTEUR")
    private String telTuteur;

    @Column(name = "ADRESSE_TUTEUR")
    private String AdresseTuteur;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_NAISS")
    private Date dateNaiss;

    @Column(name = "LIEU_NAISS")
    private String lieuNaiss;

    @Column(name = "PHOTO_FILENAME")
    private String photoFileName;

    @Column(name = "COD_TPE")
    private byte codeTpe;
    
    @Column(name = "DECLARATION")
    private boolean declaration;

    @OneToOne(mappedBy = "inscrit")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PCS_PERE")
    private Pcs pcsPere = new Pcs();

    @ManyToOne
    @JoinColumn(name = "FK_ID_PCS_MERE")
    private Pcs pcsMere = new Pcs();

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVINCE")
    private Province provinceNaiss = new Province();

    @ManyToOne
    @JoinColumn(name = "FK_ID_PAYS")
    private Pays paysNaiss = new Pays();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ID_BAC")
    private Bac Bac = new Bac();

    @Column(name = "MENTION_BAC")
    private String mentionBac;

    @ManyToOne
    @JoinColumn(name = "FK_COD_ETAPE")
    private Etape etape = new Etape();

  
     @Column(name = "DATE_CREAT")
    private Timestamp dateCreat;

    @Column(name = "DATE_MODIF")
    private Timestamp dateModif;

    @Column(name = "USER_MODIF")
    private String userModif;
    
    @Column(name = "DATE_VALID")
    private Timestamp dateValid;
    
    @Column(name = "USER_VALID")
    private String userValid;
    
    @Column(name = "DATE_INVALID")
    private Timestamp dateInvalid;
    
    @Column(name = "USER_INVALID")
    private String userInValid;


}
