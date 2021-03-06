/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.service;

import ac.encg.preins.entity.Academie;
import ac.encg.preins.entity.Admis;
import ac.encg.preins.entity.Etape;
import ac.encg.preins.entity.SerieBac;
import ac.encg.preins.entity.Inscrit;
import ac.encg.preins.entity.Pays;
import ac.encg.preins.entity.Pcs;
import ac.encg.preins.entity.Province;
import ac.encg.preins.entity.QInscrit;
import ac.encg.preins.predicate.InscritPredicate;
import ac.encg.preins.repository.AcademieRepository;
import ac.encg.preins.repository.EtapeRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import ac.encg.preins.repository.InscritRepository;
import ac.encg.preins.repository.PaysRepository;
import ac.encg.preins.repository.PcsRepository;
import ac.encg.preins.repository.ProvinceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ac.encg.preins.repository.SerieBacRepository;
import java.util.Optional;

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
    private PcsRepository pcsRepo;

    @Autowired
    private SerieBacRepository SerieBacRepo;

    @Autowired
    private AcademieRepository academieRepo;

    @Autowired
    private EtapeRepository etapeRepo;

    public void save(Inscrit inscrit) {
        inscritRepo.save(inscrit);
    }

    public List<Province> loadProvinces() {
        return provinceRepo.findAllByOrderByLibAsc();
    }

    public List<Pays> loadPays() {
        return paysRepo.findAllByOrderByLibAsc();
    }

    public List<SerieBac> loadBacs() {
        return SerieBacRepo.findAllByOrderByLibAsc();
    }
    
     public List<Pcs> loadPcsList() {
        return pcsRepo.findAllByOrderByLibAsc();
    }

    public List<Academie> loadAcademies() {
        return academieRepo.findAllByOrderByLibAsc();
    }

    public List<Inscrit> findByCneOrCin(String cne, String cin){
        
        return (List<Inscrit>) inscritRepo.findAll(InscritPredicate.existCneOrCin(cne, cin));
     //   return null;
    }
    public Optional<Inscrit> getInscritByCne(String cne) {
        return inscritRepo.findByCne(cne);
    }

    public List<Etape> loadEtapes() {
        return etapeRepo.findAllByOrderByLibAsc();
    }

    public List<Inscrit> loadAll() {
        return inscritRepo.findAll();
    }
    public Iterable<Inscrit> loadAllValid() {
        return inscritRepo.findAll(QInscrit.inscrit.isVALID.eq(Boolean.TRUE));
    }
    
    public long countInscritValidByOperator(String operator){
        
        return inscritRepo.count(QInscrit.inscrit.userValid.equalsIgnoreCase(operator));
        
    }

    public List<Inscrit> saveAll(List<Inscrit> inscrits) {
        return inscritRepo.saveAll(inscrits);
    }

    public SerieBac loadSerie() {
        return SerieBacRepo.getOne(new Long("1"));
    }

    public Academie loadAcademie() {
        return academieRepo.getOne(new Long("1"));
    }

    public Province loadProvince() {
        return provinceRepo.getOne(new Long("1"));
    }

    public Etape loadEtape() {
        return etapeRepo.getOne("GITCT1");
    }

    public Pays loadPay() {
        return paysRepo.getOne(new Long("350"));
    }
    
    

}
