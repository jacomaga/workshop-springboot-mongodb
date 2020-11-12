package com.jaco.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jaco.workshopmongo.domain.Post;
import com.jaco.workshopmongo.domain.User;
import com.jaco.workshopmongo.dto.AuthorDTO;
import com.jaco.workshopmongo.dto.CommentDTO;
import com.jaco.workshopmongo.repository.PostRepository;
import com.jaco.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));

		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("21/03/2018"),"Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem, amigo!", sdf.parse("23/03/2017"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("APROVEITA!", sdf.parse("24/03/2017"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("BOM DIA! amigo!", sdf.parse("27/03/2017"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2)); 
		post2.getComments().addAll(Arrays.asList(c3)); 

		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

	
}
