package tn.esprit.esponline.service;

import java.util.List;


import tn.esprit.esponline.entity.User;

public interface IUserService {
	
	public User ajouterUser(User user);
	public String getUserPrenomById(int userId);
	public long getNombreUserJPQL();
	public List<String> getAllUserNamesJPQL();
	public User authenticate(String login, String password);
	public long addOrUpdateUser(User user);
	public List<User> getAllUsers();
	public long removeUser(long id);
	public void changerPassword(String id, String OldPassword);
	public String authentification(String email,String password);

}
