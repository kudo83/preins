/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kudo
 */
@Entity
@Getter
@Setter
@Table(name = "USER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    private int id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "ACTIVE")
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "ID_USER"), inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSCRIT_ID_FK", referencedColumnName = "ID_INS")
    public Inscrit inscrit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADMIS_ID_FK", referencedColumnName = "ID_ADMIS")
    public Admis admis;

    public User() {
    }

    public User(User user) {
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.id = user.getId();
        this.password = user.getPassword();
    }

}
