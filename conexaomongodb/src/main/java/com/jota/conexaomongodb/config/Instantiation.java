package com.jota.conexaomongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.dto.AuthorDTO;
import com.jota.conexaomongodb.dto.ComentDTO;
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

		User suely = new User(null, "suely", "rua su", "33333");
		User joao = new User(null, "joao", "rua jo", "2222");

		userRepository.saveAll(Arrays.asList(suely, joao));

		Post post = new Post(null, sdf.parse("20/10/2023"), "Título teste", "estou viajandoe", new AuthorDTO(suely));
		Post post1 = new Post(null, sdf.parse("21/11/2023"), "Título teste1", "vou trabalhar", new AuthorDTO(suely));
		Post post2 = new Post(null, sdf.parse("22/10/2023"), "Título teste", "que dia lindo", new AuthorDTO(joao));
		Post post3 = new Post(null, sdf.parse("23/11/2023"), "Título teste1", "vamos que vamos", new AuthorDTO(joao));
		
		
		ComentDTO comentario1 = new ComentDTO("show de bola", sdf.parse("21/10/2023"), new AuthorDTO(joao));
		ComentDTO comentario2 = new ComentDTO("sva com deus", sdf.parse("22/10/2023"), new AuthorDTO(joao));	
		ComentDTO comentario3 = new ComentDTO("lindo mesmo", sdf.parse("22/10/2023"), new AuthorDTO(suely));
		ComentDTO comentario4 = new ComentDTO("vamos", sdf.parse("24/10/2023"), new AuthorDTO(suely));
		
		post.getComents().addAll(Arrays.asList(comentario1));
		post1.getComents().addAll(Arrays.asList(comentario2, comentario1));
		post2.getComents().addAll(Arrays.asList(comentario3, comentario1));
		post3.getComents().addAll(Arrays.asList(comentario4));
		
		postRepository.saveAll(Arrays.asList(post, post1, post2, post3));
		
		suely.getPosts().addAll(Arrays.asList(post, post1));
		joao.getPosts().addAll(Arrays.asList(post2, post3));
		userRepository.save(suely);

	}

}
