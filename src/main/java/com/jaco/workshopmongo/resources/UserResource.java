package com.jaco.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaco.workshopmongo.domain.Post;
import com.jaco.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class UserResource {
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post object = service.findById(id);
		return ResponseEntity.ok().body(object);
	}
	

}
