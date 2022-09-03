package com.appservices.service;

import com.appservices.model.user;
import com.appservices.response.InfoResponse;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends ICRUD<user, UUID>{
    Page<user> listarPageable(Pageable pageable);
    
    public user findByEmail(String email);
    
    InfoResponse saveUser(user usuarioRequest);
    
    InfoResponse searchUser(String email);
    
    InfoResponse deleteUser(String email);
}