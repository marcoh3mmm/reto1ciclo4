/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reto1ciclo4.reto1ciclo4.controller;

import com.reto1ciclo4.reto1ciclo4.model.User;
import com.reto1ciclo4.reto1ciclo4.service.UserService;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")

/**
 *
 * @author Marco Moreno
 */
public class UserController {
    
    @Autowired
    private UserService userservice;

    @GetMapping("/user/all")
    public List<User> listarUsuarios(){
        return userservice.getUsers();
    }

    @PostMapping("/user/new")
    @ResponseStatus(code=HttpStatus.CREATED)
    public User crearUsuario(@RequestBody User entityUser) {
        return userservice.guardarUser(entityUser);
    }
    
    @GetMapping("/user/{email}")
    public ResponseEntity<?> buscarCorreo(@PathVariable String email) {
        
        Optional <User> optional = userservice.getEmail(email);
        
        Boolean Encontrado = false;
        
        if(optional.isPresent()){
            Encontrado=true;
        }else{
            Encontrado = false;
        }
        
        return ResponseEntity.ok().body(Encontrado);
    }
    
    @GetMapping("/user/{email}/{password}")
    public ResponseEntity<?> buscarCorreoPass(@PathVariable String email,@PathVariable String password) {
        
        List <User> optional = userservice.getEmailPass(email,password);
        System.out.println(optional);

        if(optional.size()==0){

            User noencontrado = new User();
            noencontrado.setId(null);
            noencontrado.setEmail(email);
            noencontrado.setPassword(password);
            noencontrado.setName("NO DEFINIDO");
            return ResponseEntity.ok().body(noencontrado);
            
        }else{
            return ResponseEntity.ok().body(optional.get(0));
        }
    }
    
}
