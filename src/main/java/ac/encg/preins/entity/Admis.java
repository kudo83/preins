/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aissam
 */
@Entity
@Getter
@Setter
@Table(name = "ADMIS",uniqueConstraints={@UniqueConstraint(columnNames={"CNE","CIN"})})
public class Admis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ADMIS")
    private int id;

    @Column(name = "CNE")
    @NotNull
    private String cne;

    @Column(name = "CIN")
    @NotNull
    private String cin;

    @Column(name = "NOM")
    @NotNull
    private String nom;
    
    @OneToOne(mappedBy = "admis")
    private User user;
    
    public Admis() {
    }

}
