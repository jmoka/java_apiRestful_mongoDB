package com.jota.conexaomongodb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jota.conexaomongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// extende dos os métodos da interface MongoRepository
	
}