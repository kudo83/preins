/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kudo
 */
public class CommonHelper {


    public static String formateDate(LocalDate date) {
       DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return date.format(formatters);
    }
}
