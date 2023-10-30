package com.jota.conexaomongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.repository.PostRepository;
import com.jota.conexaomongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		User maria = new User(null, "suely", "rua su", "33333");
		
		 userRepository.saveAll(Arrays.asList(maria));

	        Post post = new Post(null, sdf.parse("20/10/2023"), "Título teste", "Corpo do post teste", maria);
	        Post post1 = new Post(null, sdf.parse("20/11/2023"), "Título teste1", "Corpo do post teste1", maria);

	     postRepository.saveAll(Arrays.asList(post, post1));
		
		
		
		
		
	}

}
