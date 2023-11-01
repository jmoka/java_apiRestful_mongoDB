package com.jota.conexaomongodb.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.resources.util.URL;
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
	
	@GetMapping(value = "/titlesearch")
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findbytitle(@RequestParam(value = "text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text); // decodifica o texto
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping(value = "/titlesearchmongo")
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findbytitlemongo(@RequestParam(value = "text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text); // decodifica o texto
		List<Post> list = postService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullsearch")
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullsearch(
			
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate	) {
		
		text = URL.decodeParam(text); // decodifica o texto
		Date min = URL.convertDate(minDate, new Date(0L)); // case de errado gera uma data padrão - new Date(0L)
		Date max = URL.convertDate(maxDate, new Date()); // caso de errado gera a data atual - new Date()
		
		List<Post> list = postService.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);

	}
	
	
	
	
	


}
