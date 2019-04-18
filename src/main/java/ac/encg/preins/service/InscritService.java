/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.service;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Bac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Province;
import ac.encg.preins.repository.AcademieRepository;
import ac.encg.preins.repository.BacRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import ac.encg.preins.repository.InscritRepository;
import ac.encg.preins.repository.PaysRepository;
import ac.encg.preins.repository.ProvinceRepository;
import java.util.List;
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
    private InscritRepository inscritRepo;
    
    @Autowired
    private ProvinceRepository provinceRepo;
    
    @Autowired
    private PaysRepository paysRepo;
    
    @Autowired
    private BacRepository bacRepo;
    
    @Autowired
    private AcademieRepository academieRepo;
  

    public void save(Inscrit inscrit) {
        inscritRepo.save(inscrit);
    }
    
    public List<Province> loadProvinces(){
        return provinceRepo.findAllByOrderByLibAsc();
    }
    
    public List<Pays> loadPays(){
        return paysRepo.findAllByOrderByLibAsc();
    }
    
    public List<Bac> loadBacs(){
        return bacRepo.findAllByOrderByLibAsc();
    }
    
    public List<Academie> loadAcademies(){
        return academieRepo.findAllByOrderByLibAsc();
    }
}
