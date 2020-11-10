package com.jaco.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaco.workshopmongo.domain.User;
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
}
