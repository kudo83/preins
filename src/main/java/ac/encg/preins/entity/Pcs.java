/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kudo
 */

@Entity
@Getter
@Setter
@Table(name = "PCS")
public class Pcs implements Serializable {
    @Id
    @Column(name = "ID_PCS")
    private Long id;
    
    @Column(name = "LIB_PCS")
    private String lib;
}
