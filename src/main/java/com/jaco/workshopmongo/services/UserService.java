package com.jaco.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaco.workshopmongo.domain.User;
import com.jaco.workshopmongo.dto.UserDTO;
import com.jaco.workshopmongo.repository.UserRepository;
import com.jaco.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> object = repo.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User object) {
		return repo.insert(object);
	}
	
	public void delete(String id ) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objectDto) {
		return new User(objectDto.getId(),objectDto.getEmail(),objectDto.getName());
	}
	
	public User update(User object) {
		User newObject = findById(object.getId());
		updateData(newObject, object);
		return repo.save(newObject);
	}
	
	public void updateData(User newObject, User object) {
		newObject.setName(object.getName());
		newObject.setEmail(object.getEmail());
	}
}
