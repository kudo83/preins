package ac.encg.preins.service;

import ac.encg.preins.entity.Admis;
import ac.encg.preins.repository.AdmisRepository;
import ac.encg.preins.repository.InscritRepository;
import ac.encg.preins.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author Aissam
 */
@Service
public class StatService {

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

}
