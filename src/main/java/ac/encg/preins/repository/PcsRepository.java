/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.repository;

import ac.encg.preins.entity.Pcs;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aissam
 */
public interface PcsRepository extends JpaRepository<Pcs, Long> {
      public List<Pcs> findAllByOrderByLibAsc();
}
