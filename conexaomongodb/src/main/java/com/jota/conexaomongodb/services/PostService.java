package com.jota.conexaomongodb.services;

import java.util.Date;
import java.util.List;

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
	
	public List<Post> findByTitle(String text){
		return postRepository.findByTitleContainingIgnoreCase(text);
	}

	public List<Post> findByTitleMong(String text){
		return postRepository.findByTitleMongo(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//acrescentar um dia na data maxima, devido as detas seram armazenada em mil segundos, para que seja contado ate 24 do proximo dia
		
		maxDate = new Date(maxDate.getTime()+24*60*60*1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
