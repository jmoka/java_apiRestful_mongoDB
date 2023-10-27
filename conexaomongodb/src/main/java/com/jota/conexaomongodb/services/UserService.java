package com.jota.conexaomongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;  // mecanismo de injeção de dependencia automatica do spring boot
	
	public List<User> findAll(){
		return userRepository.findAll();
		
	}
	

}
