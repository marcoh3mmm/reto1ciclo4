/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reto1ciclo4.reto1ciclo4.repository;

import com.reto1ciclo4.reto1ciclo4.model.User;
import java.util.List;
import java.util.Optional;



import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Marco Moreno
 */
public interface UserCrudRepository extends CrudRepository<User,Long>{
    
    public Optional<User> findByEmail(String email);

    public List<User> findByEmailAndPassword(String email,String password);
    
}
