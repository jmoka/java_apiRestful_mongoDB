package com.jota.conexaomongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jota.conexaomongodb.domain.User;
import com.jota.conexaomongodb.dto.UserDTO;
import com.jota.conexaomongodb.repository.UserRepository;
import com.jota.conexaomongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; // mecanismo de injeção de dependencia automatica do spring boot

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não Encontrado");

		}
		return user;
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	
	public User delete(String id) {
		findById(id);
		userRepository.deleteById(id);
		return null;
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
		}
	
	
	private void updateData(User newObj, User obj) {
		newObj.setNome(obj.getNome());
		newObj.setEndereco(obj.getEndereco());
		newObj.setTelefone(obj.getTelefone());
		
	}

	// pega um DTO e estancia um Usuário a partir dele
	public User FromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getNome(), objDto.getEndereco(), objDto.getTelefone());
	}
	


}
