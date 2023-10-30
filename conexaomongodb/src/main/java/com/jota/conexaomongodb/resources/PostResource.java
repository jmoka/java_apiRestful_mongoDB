package com.jota.conexaomongodb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	
	@GetMapping(value = "/{id}")
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findbyId(@PathVariable String id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);

	}

}
