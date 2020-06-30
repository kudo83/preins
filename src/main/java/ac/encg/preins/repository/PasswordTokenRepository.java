/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.repository;

import ac.encg.preins.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kudo
 */
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>{

    public PasswordResetToken findByToken(String token);



}