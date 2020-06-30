/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "PASSWORD_REST_TOKEN")
public class PasswordResetToken {
  
    private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PWDRESTTOKEN")
    private Long id;
  
    @Column(name = "TOKEN")
    private String token;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ID_USER_FK")
    private User user;
    
    @Column(name = "USED")
    private boolean used;
  
    //Non utilisé pour l'instant (Date serveur n'est pas toujours fiable)
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public PasswordResetToken() {
    }

   
}