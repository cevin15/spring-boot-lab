package com.youbenzi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;

	@GetMapping(path = "/add")
	public String addNewUser(@RequestParam String name, @RequestParam int age) {

		User n = new User();
		n.setName(name);
		n.setAge(age);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/find")
	public List<User> findByName(@RequestParam int age) {
		return userRepository.findByAgeLessThan(age);
	}
	
	@GetMapping(path = "/update")
	public String update(@RequestParam int age, @RequestParam long id) {
		userRepository.updateAge(age, id);
		return "Updated";
	}
	
}