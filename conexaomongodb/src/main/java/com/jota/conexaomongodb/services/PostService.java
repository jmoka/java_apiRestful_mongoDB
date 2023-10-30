package com.jota.conexaomongodb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.repository.PostRepository;
import com.jota.conexaomongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository; // mecanismo de injeção de dependencia automatica do spring boot

	
	public Post findById(String id) {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new ObjectNotFoundException("Objeto não Encontrado");

		}
		return post;
	}


}
