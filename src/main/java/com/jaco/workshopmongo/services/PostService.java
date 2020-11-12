package com.jaco.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaco.workshopmongo.domain.Post;
import com.jaco.workshopmongo.repository.PostRepository;
import com.jaco.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {
		return repo.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> object = repo.findById(id);
		return object.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
