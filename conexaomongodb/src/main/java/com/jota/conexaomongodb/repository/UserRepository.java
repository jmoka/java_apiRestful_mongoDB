package com.jota.conexaomongodb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jota.conexaomongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	// extende dos os métodos da interface MongoRepository
	
}