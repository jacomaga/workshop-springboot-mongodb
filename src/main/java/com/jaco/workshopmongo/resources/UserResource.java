package com.jaco.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jaco.workshopmongo.domain.User;
import com.jaco.workshopmongo.dto.UserDTO;
import com.jaco.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(object -> new UserDTO(object)).collect(Collectors.toList());
	
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User object = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(object));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objectDto){
		
		User object = service.fromDTO(objectDto);
		object = service.insert(object);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	
	}
}
