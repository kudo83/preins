/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.repository;

import ac.encg.preins.entity.Inscrit;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 *
 * @author Aissam
 */
public interface InscritRepository extends JpaRepository<Inscrit, Long>, QuerydslPredicateExecutor<Inscrit> {

}
