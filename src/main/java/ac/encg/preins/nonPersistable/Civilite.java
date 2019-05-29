/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.nonPersistable;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author kudo
 */


@Getter
@Setter
public class Civilite {

    public Civilite(Byte codCiv, String libCiv) {
        this.codCiv = codCiv;
        this.libCiv = libCiv;
    }
    private Byte codCiv;
    private String libCiv;
}
