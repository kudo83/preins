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
public class LoggedUser {
    private String username;
    private String password;
}
