/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.predicate;

import ac.encg.preins.entity.QInscrit;
import com.querydsl.core.types.Predicate;

/**
 *
 * @author kudo
 */
public final class InscritPredicate {
    public static Predicate hasCne(String cne){
        return QInscrit.inscrit.cne.eq(cne);
    }
    
}
