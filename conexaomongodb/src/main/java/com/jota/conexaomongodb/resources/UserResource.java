package com.jota.conexaomongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.dto.UserDTO;
import com.jota.conexaomongodb.services.UserService;

import ch.qos.logback.core.model.processor.PhaseIndicator;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	 
	@Autowired
	private UserService userService;
	
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		
		// recebe a lista de todos usuarios , porém precisa converter essa lista para uma lista UserDTO
		List<User> list = userService.findAll();
		
		// converter uma lista e listaDTO
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	
	
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		@GetMapping (value = "/{id}")
		public ResponseEntity<UserDTO> findbyId(@PathVariable String id) {
			User user = userService.findById(id);			
			return ResponseEntity.ok().body(new UserDTO(user));

		}
	
	
	
	

}
