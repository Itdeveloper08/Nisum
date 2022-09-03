package com.appservices.repo;

import com.appservices.model.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUser extends IGenericRepo<user, UUID>{
	
	@Query("from user  where EMAIL = :email ")
	user findByEmail(@Param("email") String email);

}

