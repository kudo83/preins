package ac.encg.preins.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String lib;

    public Role() {
    }

    
}
