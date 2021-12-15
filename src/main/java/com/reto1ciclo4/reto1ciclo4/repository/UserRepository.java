/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reto1ciclo4.reto1ciclo4.repository;

import com.reto1ciclo4.reto1ciclo4.model.User;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marco Moreno
 */
@Repository
public class UserRepository {
    
    @Autowired
    private UserCrudRepository userRepo;

    public List<User> getAllUsers(){
        return (List<User>) userRepo.findAll();
    }

    public User saveUser(User userEntity){
        return userRepo.save(userEntity);
    }

    public Optional<User> findEmail(String email){
        return userRepo.findByEmail(email);
    }
    public List<User> findEmailAndPass(String email, String password){
        return userRepo.findByEmailAndPassword(email,password);
    }
    
}
