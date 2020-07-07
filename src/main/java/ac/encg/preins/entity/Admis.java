/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
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
public class Admis {

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
    @Temporal(DATE)
    private Date dateCREAT;

    @OneToOne(mappedBy = "admis")
    private User userCreat;

    @Column(name = "DATE_MODIF")
    @Temporal(DATE)
    private Date dateModif;

    @OneToOne(mappedBy = "admis")
    private User userModif;

 
    public Admis() {
    }

    

}
