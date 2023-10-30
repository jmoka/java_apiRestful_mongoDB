package com.jota.conexaomongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jota.conexaomongodb.domain.Post;
import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.dto.UserDTO;
import com.jota.conexaomongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	// @RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		// recebe a lista de todos usuarios , porém precisa converter essa lista para
		// uma lista UserDTO
		List<User> list = userService.findAll();

		// converter uma lista e listaDTO
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findbyId(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));

	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
		User user = userService.FromDTO(objDTO);
		user.setId(id);
		user = userService.update(user);
		// quando a sua resposta não retorna nada o codigo é 204
		return ResponseEntity.noContent().build();

	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		// quando a sua resposta não retorna nada o codigo é 204
		return ResponseEntity.noContent().build();

	}

	// @RequestMapping(method = RequestMethod.POS)
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {
		User user = userService.FromDTO(objDTO);
		user = userService.insert(user);

		// codigo para retornar o objeto criado ou seja , vai retornar o User que foi
		// criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

		// created retorna o codigo 201 que é o codigo de resposta http quando é criado
		// um novo recurso

		// retorna o resposta vazia com codigo 201 e o cabeçalho contando a localilzação
		// do novo recurso criado
		return ResponseEntity.created(uri).build();
	}
	
	
	// @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
		@GetMapping(value = "/{id}/posts")
		public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getPosts());
	
		}
	
	

}
