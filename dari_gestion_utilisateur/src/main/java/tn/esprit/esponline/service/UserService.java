package tn.esprit.esponline.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.esponline.entity.Role;
import tn.esprit.esponline.entity.User;

import tn.esprit.esponline.repository.IUserRepository;




@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserRepository er;
	@Autowired
	IUserRepository userRepo;
	

	
	private static final Logger l= LogManager.getLogger(UserService.class);

	@Override
	public User ajouterUser(User user) {
		return er.save(user);
	}
	
	
	
	@Override
	@Transactional
	public List<User> getAllUsers() {
	return (List<User>) er.findAll();
	}
	
	@Override
	@Transactional
	public long addOrUpdateUser(User user) {
	er.save(user);
	return user.getId();
	}


	@Transactional
	//IF department is empty we add new List and add an employee and set it to the department eles we add that employee



	

	@Override
	public String getUserPrenomById(int userId) {
		String empPrenom = er.findById((long) userId).get().getPrenom();
		return empPrenom;
	}

	@Override
	public long getNombreUserJPQL() {
		int count = er.getNombreUser();
		return (long) count;
	}

	@Override
	public List<String> getAllUserNamesJPQL() {
		List<String> namesList = er.getUserNames();
		return namesList;
	}
	
	@Override
	public User authenticate(String login, String password) {
	return er.getUserByEmailAndPassword(login, password);
	}
	@Override
	public void changerPassword(String id, String OldPassword) {
		
		Long j = Long.parseLong(id);
		List<User> users =(List<User>) userRepo.findAll();
		for(User user:users) {
			if(user.getId().equals(j)) {
				user.setPassword(OldPassword);
				userRepo.save(user);
			}
		}
	}
	

	@Override
	@Transactional
	public long removeUser(long id) {
		er.deleteById(id);
		l.info("removed");
		
		return 0;
	}
	
	@Override
	public String authentification(String email, String password) {
		List<User> users =(List<User>) userRepo.findAll();
		int verifyemail=0;
		int verifypassword=0;
		for(User user:users) {
			if(user.getEmail().equals(email)) {
				verifyemail++;
				
				if(user.getPassword().equals(password)) {
					verifypassword++;
					if(user.getRole()== Role.ADMINISTRATEUR) {
						//System.out.println();
						return("Welcome Admin");
						
					}else if(user.getRole()== Role.CLIENT) {
						if(user.getRole().CLIENT.isBlock()) {
							System.out.println(user.getRole().CLIENT.getDescriptionBlock());
							
						}else {
							return("Welcome Client");
							
						}
						
					}
					else if(user.getRole()== Role.AGENT){
						return("Welcome agent");
						
					}
				}else {
					if (user.getRole()== Role.CLIENT) {
						int nbre=(user.getRole().CLIENT).getNbre();
						(user.getRole().CLIENT).setNbre(nbre+1);
						userRepo.save(user);
						if((user.getRole().CLIENT).getNbre()==3) {
							(user.getRole().CLIENT).setBlock(true);
							(user.getRole().CLIENT).setDescriptionBlock("security problem");
							userRepo.save(user);
							return("votre compte est blocke security problem" );
							
							
						}else if((user.getRole().CLIENT).getNbre()>=3) {
							(user.getRole().CLIENT).setNbre(3);
							userRepo.save(user);
							return("votre compte est blocke security problem" );
						}else {
							return("password incorrect");
						}
					
					
					
				}
				
			}
				
				
			}
		}
		if(verifyemail==0) {
			return("email not found ");
		}else {
			return("");
		}
			
		
		
	}



}
