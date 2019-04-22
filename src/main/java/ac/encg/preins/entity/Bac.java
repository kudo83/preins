/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "BAC")
public class Bac implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_BAC")
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "FK_ID_SERIE_BAC")
    private SerieBac SerieBac = new SerieBac();

    @ManyToOne
    @JoinColumn(name = "FK_ID_PROVINCE")
    private Province provinceBac = new Province();

    @Column(name = "COD_MNB")
    private String montion;

    @Column(name = "ANN")
    private String Annee;

    @ManyToOne
    @JoinColumn(name = "FK_ID_ACADEMIE")
    private Academie academie = new Academie();

}
