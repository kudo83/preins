package ac.encg.preins.service;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.repository.AdmisRepository;
import ac.encg.preins.predicate.AdmisPredicate;
import java.util.List;
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

    /**
     *
     * @param users
     */
    public void saveAdmisList(List<Admis> admisList) {
        admisRepo.saveAll(admisList);

    }

    public void save(Admis user) {
        admisRepo.save(user);
    }
    
    public List<Admis> findByCneOrCin(String cne, String cin){
        
        return (List<Admis>) admisRepo.findAll(AdmisPredicate.existCneOrCin(cne, cin));
     //   return null;
    }

}
