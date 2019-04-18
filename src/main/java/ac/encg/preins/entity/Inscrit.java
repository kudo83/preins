/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    @Column(name = "TEL")
    private String tel;
    
    @Column(name = "EMAIL")
    private String email;
    
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
    
     @Column(name = "ADRESSE_TUTEUR")
    private String AdresseTuteur;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATE_NAISS")
    private Date dateNaiss;
    
    @Column(name = "LIEU_NAISS")
    private String lieuNaiss;

    @ManyToOne
    private Province provinceNaiss;
    
    @ManyToOne
    private Pays paysNaiss;

    private InscritBac inscritBac;
}
