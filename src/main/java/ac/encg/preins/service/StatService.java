package ac.encg.preins.service;

import ac.encg.preins.entity.QInscrit;
import ac.encg.preins.repository.AdmisRepository;
import ac.encg.preins.repository.InscritRepository;
import ac.encg.preins.repository.UserRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Aissam
 */
@Service
public class StatService implements Serializable{

    @Autowired
    private AdmisRepository admisRepo;
    
    @Autowired
    private InscritRepository inscritRepo;
    
     @Autowired
    private UserRepository userRepo;


    public long countAdmis() {
        return admisRepo.count();
    }
    
    
    public long countInscrits() {
        return inscritRepo.count();
    }
    
    public long countUsers() {
        return userRepo.count();
    }
    
    public long countInscritValid(){
         return inscritRepo.count(QInscrit.inscrit.isVALID.eq(Boolean.TRUE));
    }

}
