package com.example.demorestfulwebservices.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
public class UserJPAResource {

	@Autowired
	UserDaoService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path="/jpa/users")
	public List<User> retrieveUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		Optional<User> user=userRepository.findById(id);
		
		Resource<User> resource=new Resource<User>(user.get());
		//HATEOAS link
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveUsers());
		resource.add(linkTo.withRel("all-users"));
		if(!user.isPresent())
		{
			throw new UserNotFoundException("id : "+id);
		}
		return resource;
	}
	
	//output- CREATED and URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User us=userRepository.save(user);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(us.getId()).toUri();
		
		
		return new ResponseEntity(user,HttpStatus.FOUND);
		//created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		
		
	}
}
