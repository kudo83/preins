package ac.encg.preins.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ROLE")
    private int id;

    @Column(name = "LIB_ROLE")
    private String lib;

    public Role() {
    }

    
}
