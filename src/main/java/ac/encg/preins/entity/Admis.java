/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aissam
 */
@Entity
@Getter
@Setter
@Table(name = "ADMIS")
public class Admis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ADMIS")
    private int id;

    @Column(name = "CNE")
    private String cne;

    @Column(name = "CIN")
    private String cin;

    @Column(name = "NOM")
    private String nom;

    @OneToOne(mappedBy = "admis")
    private User user;

    @Column(name = "DATE_CREAT")
    private Timestamp dateCREAT;

    
    @Column(name = "USER_CREAT")
    private String userCreat;

    @Column(name = "DATE_MODIF")
    private Timestamp dateModif;

    @Column(name = "USER_MODIF")
    private String userModif;

 
    public Admis() {
    }

    

}
