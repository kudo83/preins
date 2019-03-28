/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Long id;

    private String nom;
    private String prenom;
    private String nomAr;
    private String prenomAr;
    private String cne;
    private String cin;
  //  @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaiss;
}
