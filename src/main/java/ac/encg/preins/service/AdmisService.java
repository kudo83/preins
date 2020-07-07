package ac.encg.preins.service;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.repository.AdmisRepository;
import ac.encg.preins.predicate.AdmisPredicate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kudo
 */
@Service
public class AdmisService {

    @Autowired
    private AdmisRepository admisRepo;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void saveAdmisList(List<Admis> admisList) {
        admisRepo.saveAll(admisList);

    }

    public void save(Admis user) {
        admisRepo.save(user);
    }
    

    public Optional<Admis> findByCneOrCin(String cne, String cin) {

        return  admisRepo.findOne(AdmisPredicate.existCneOrCin(cne, cin));
    }
    
    public Optional<Admis> findByCne(String cne) {

        return  admisRepo.findOne(AdmisPredicate.existCne(cne));
    }
    
     public Optional<Admis> findByCin(String cin) {

        return  admisRepo.findOne(AdmisPredicate.existCin(cin));
    }

    public boolean existByCne(String cne) {

        return admisRepo.exists(AdmisPredicate.existCne(cne));
    }

    public boolean existByCin(String cin) {

        return admisRepo.exists(AdmisPredicate.existCin(cin));
    }

    public boolean existByCneOrCin(String cne, String cin) {

        return admisRepo.exists(AdmisPredicate.existCneOrCin(cne, cin));
    }

    public List<Admis> loadAll() {
        return admisRepo.findAll();
    }

}
