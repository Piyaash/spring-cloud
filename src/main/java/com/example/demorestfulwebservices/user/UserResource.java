package com.example.demorestfulwebservices.user;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
public class UserResource {

	@Autowired
	UserDaoService userService;
	
	@GetMapping(path="/users")
	public List<User> retrieveUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> getUser(@PathVariable int id) {
		User user=userService.findOne(id);
		
		Resource<User> resource=new Resource<User>(user);
		//HATEOAS link
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveUsers());
		resource.add(linkTo.withRel("all-users"));
		if(user==null)
		{
			throw new UserNotFoundException("id : "+id);
		}
		return resource;
	}
	
	//output- CREATED and URI
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User us=userService.insert(user);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(us.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user=userService.delete(id);
		if(user==null)
		{
			throw new UserNotFoundException("id : "+id);
		}
		
	}
}
