package com.jota.conexaomongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jota.conexaomongodb.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	 
	//@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		User maria = new User("1", "maria", "rua maria", "34555");
		User jota = new User("2", "jota", "rua jota", "2222");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, jota));

		return ResponseEntity.ok().body(list);

	}

}
