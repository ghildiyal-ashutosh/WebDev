package com.example.webdevsummer22018serverashu95.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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

public class UserService 
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
			
			current.setUsername(newUser.getUsername());
			current.setFirstName(newUser.getFirstName());
			current.setLastName(newUser.getLastName());
			current.setPassword(newUser.getPassword());
			current.setRole(newUser.getRole());
			userRepository.save(current);
			return current;
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
	public User register(@RequestBody User user , HttpSession session) 
	{ 
		
		String name = user.getUsername();
		
		Optional<User> data = userRepository.findUserByUsername(name);
		if (data.isPresent())
		{
			User user1 = new User();
			user1.setFirstName("Negative");
			return user1;
		}
		else {
			
			User currentUser = userRepository.save(user);
			session.setAttribute("currentUser", currentUser);
			return currentUser;
			
		}
	}
	
	@GetMapping("/api/user/checkCurrentUser")
	public Optional<User> checkLogIn(HttpSession session)
	{
		User currentUser = (User) session.getAttribute("currentUser");
		return (userRepository.findById(currentUser.getId()));
	}
	
	
    @PostMapping("/api/user/logIn")
    public User login(@RequestBody User user, HttpSession session) 
    {
   	 String username = user.getUsername();
   	 String password = user.getPassword();
   	 
   	 Optional<User> data = userRepository.findUserByCredentials(username, password);
   	 if (data.isPresent())
   	 {
   		 session.setAttribute("currentUser", data.get());
   		 return data.get();
   		 
   	 }
   	 else {
   		 User user1 = new User();
   		 user1.setFirstName("Negative");
   		 		return user1;
   	 }
    }
    
    @PostMapping("/api/user/logOut")
    public void logout (HttpSession session) 
    {
    	session.invalidate();
    }

    
	@PutMapping ("api/user/updateProfile/{userId}")
	public User updateProfile(@PathVariable ("userId") int userId, @RequestBody User newUser)
	{
		Optional<User> data = userRepository.findById(userId);
		
		if (data.isPresent())
		{
			User current = data.get();
			
			current.setContact(newUser.getContact());
			current.setEmail(newUser.getEmail());
			current.setDateOfBirth(newUser.getDateOfBirth());
			current.setRole(newUser.getRole());
			userRepository.save(current);
			return current;
		}
		else
		{
			User user = new User();
			user.setFirstName("Negative");
			return user;
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
