package com.example.demorestfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users=new ArrayList<>();
	private static int usersCount=4;
	static {
		users.add(new User(1,"Piya",new Date()));
		users.add(new User(2,"hiya",new Date()));
		users.add(new User(3,"Param",new Date()));
		users.add(new User(4,"Prabh",new Date()));
		
	}

	public List<User> findAll(){
		return users;
	}
	
	public User insert(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User u:users) {
			if(u.getId()==id)
				return u;
		}
		return null;	
	}
	
	public User delete(int id) {
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext()) {
			User user=iterator.next();
			if(user.getId()==id) {
			
				iterator.remove();return user;
			}
		}
		return null;	
	}
}
