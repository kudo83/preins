/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.repository;

import ac.encg.preins.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kudo
 */
public interface UserRepository extends JpaRepository<User, Long> {
 
//   User findByName(String username);
    Optional<User> findByName(String username);
}