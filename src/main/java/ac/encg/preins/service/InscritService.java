/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.service;

import ac.encg.preins.entity.Inscrit;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import ac.encg.preins.repository.InscritRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aissam
 */

@Service
@Qualifier("inscritService")
public class InscritService implements Serializable {

    @Autowired
    private InscritRepository repository;
  

    public void save(Inscrit inscrit) {
        repository.save(inscrit);
    }
}
