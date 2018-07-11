package com.example.webdevsummer22018serverashu95.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverashu95.models.User;
import com.example.webdevsummer22018serverashu95.repositories.UserRepository;

@RestController

public class UserServices 
{
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers()
	{
		return (List<User>) userRepository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUserById(@PathVariable ("userId") int userId)
	{
		userRepository.deleteById(userId);
	}

	@GetMapping("/api/user/{userId}")
	public Optional <User> findUserById(@PathVariable ("userId") int userId)
	{
		return (userRepository.findById(userId));
	}
	
	@PutMapping ("api/user/{userId}")
	public User updateUser(@PathVariable ("userId") int userId, @RequestBody User newUser)
	{
		Optional<User> data = userRepository.findById(userId);
		
		if (data.isPresent())
		{
			User current = data.get();
			current.setContact(newUser.getContact());
			current.setUsername(newUser.getUsername());
			current.setFirstName(newUser.getFirstName());
			current.setLastName(newUser.getLastName());
			current.setPassword(newUser.getPassword());
			current.setEmail(newUser.getEmail());
			current.setDateOfBirth(newUser.getDateOfBirth());
			current.setRole(newUser.getRole());
			return userRepository.save(current);
		}
		
		else
		{
			User user = new User();
			user.setFirstName("Negative");
			return user;
		}

}
	@GetMapping("/api/user/username/{username}")
	public User findUserByUsername(@PathVariable ("username") String uname)
	{
		Optional<User> data = userRepository.findUserByUsername(uname);
		
		if (data.isPresent())
		{
			User user = data.get();
			return user;
		}
		else
		{
			User user = new User();
			user.setFirstName("Negative");
			return user;
		}
	}
	
	
	@PostMapping("/api/user/register")
	public User register(@RequestBody User user) { 
		
		String name = user.getUsername();
		
		Optional<User> data = userRepository.findUserByUsername(name);
		if (data.isPresent())
		{
			User user1 = new User();
			user1.setFirstName("Negative");
			return user1;
		}
		else {
			return (userRepository.save(user));
			
		}
	}
	
	
    @PostMapping("/api/user/logIn")
    public User login(@RequestBody User user) 
    {
   	 String username = user.getUsername();
   	 String password = user.getPassword();
   	 
   	 Optional<User> data = userRepository.findUserByCredentials(username, password);
   	 if (data.isPresent())
   	 {
   		 return data.get();
   	 }
   	 else {
   		 User user1 = new User();
   		 user1.setFirstName("Negative");
   		 		return user1;
   	 }
    }
    
    @PutMapping("/api/user/reset")
    public User resetPassword(@RequestBody User user)
    {
    	String contact = user.getContact();
    	String email = user.getEmail();
    	Optional<User> data = userRepository.findUserByContact(contact, email);
    	if (data.isPresent())
    	{
    	 User users = data.get();
    	 users.setPassword(user.getPassword());
    	 userRepository.save(users);
    	 return users;
    	}
    	else {
    		User users = new User();
    		users.setFirstName("Negative");
    		return users;
    	}
     }
	
}
