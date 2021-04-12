package tn.esprit.esponline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.esponline.entity.User;
import tn.esprit.esponline.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService es;
	
	@GetMapping("/getNombreUseres") 
	@ResponseBody
	public long getEmpNumber(){
		long count = es.getNombreUserJPQL();
		return count;
	}
	
	@GetMapping("/getAllUseres")
	@ResponseBody
	public List<User> getListUsers(){
		return es.getAllUsers();
	}
	
	@GetMapping("/getListeNom")
	@ResponseBody
	public List<String> getEmpNames(){
		List<String> names = es.getAllUserNamesJPQL();
		return names;
	}
	
	@PostMapping("/addUser")
	@ResponseBody
	public User add(@RequestBody User e){
		User emp = es.ajouterUser(e);
		return emp;
	}
	
	@DeleteMapping("/removeUser/{idE}")
	@ResponseBody
	public void removeUser(@PathVariable("idE") int idE){
		es.removeUser(idE);
	}
	
	
	@PutMapping("/updateUser/{idE}")
	@ResponseBody
	public void updateUser(@PathVariable("idE") int idE, @RequestBody User e)
	
		{ es.addOrUpdateUser(e); } 
	
	@PutMapping("/updatepass/{idE}")
	@ResponseBody
	public void updatepass(@PathVariable("idE") int idE,@RequestBody String id, @RequestBody String OldPassword)
	
		{ es.changerPassword(id, OldPassword); } 
	

	@GetMapping("/authentification/{email}/{password}") 
	 @ResponseBody 
	 //http://localhost:8081/SpringMVC/servlet/getAnnoncefollower 
	 public String authentification(@PathVariable("email") String email,@PathVariable("password") String password) { 
		return es.authentification(email, password);
		  
	}
	 
	
}
