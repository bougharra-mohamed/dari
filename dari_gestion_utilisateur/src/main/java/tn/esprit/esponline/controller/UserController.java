package tn.esprit.esponline.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;


import tn.esprit.esponline.entity.User;
import tn.esprit.esponline.entity.Role;
import tn.esprit.esponline.service.UserService;

@Scope(value = "session")
@Controller(value = "userController")

@ELBeanName(value = "userController") // nous permettons d'acceder les
											// variable a partir du code html
											// ex: usereController.Login
@Join(path = "/", to = "/login.jsf")
public class UserController {

	@Autowired
	private UserService es;

	private String login;
	private String password;
	private User user;
	private Boolean loggedIn;
	private User authenticatedUser;
	private String prenom;
	private String nom;
	private String email;
	private Role role;
	private List<User> users;
	private long userIdToBeUpdated;

	


	public String addUser() {
		String navigateTo = null;
		if (authenticatedUser==null || !loggedIn) return "/login.xhtml";
		es.addOrUpdateUser(((new User(nom, prenom, email, password, role))));
		navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
		return navigateTo;
	}
	
	public List<User> getListEmpComboBox(){
		List<User> empComboBox = new ArrayList<User>();
		for (User user : es.getAllUsers()) {
			empComboBox.add(new User(user.getId(),user.getNom()));
		}
		return empComboBox;
	}

	public void removeUser(long userId) {
		es.removeUser(userId);
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	
	public void displayUser(User empl)
	{
	this.setPrenom(empl.getPrenom());
	this.setNom(empl.getNom());
	this.setEmail(empl.getEmail());
	this.setRole(empl.getRole());
	this.setPassword(empl.getPassword());
	this.setUserIdToBeUpdated(empl.getId());
	}
	
	public void updateUser()
	{ es.addOrUpdateUser(new User(userIdToBeUpdated, nom,
	prenom, email, password, role)); }

	public List<User> getUsers() {
		users = es.getAllUsers();
		return users;
	}
	
	

	public String doLogin() {
		String navigateTo = "null";
		authenticatedUser = es.authenticate(login, password);
		if (authenticatedUser != null && authenticatedUser.getRole() == Role.ADMINISTRATEUR) {
			navigateTo = "/pages/admin/welcome.xhtml?faces-redirect=true";
			setLoggedIn(true);
		} else {
			FacesMessage facesMessage = new FacesMessage(
					"Login Failed: please check your username/password and try again.");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getEs() {
		return es;
	}

	public void setEs(UserService es) {
		this.es = es;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role[] getRoles() {
		return Role.values();
	}

	public long getUserIdToBeUpdated() {
		return userIdToBeUpdated;
	}

	public void setUserIdToBeUpdated(Long long1) {
		this.userIdToBeUpdated = long1;
	}
	
	




	
	public List<SelectItem> getAllUseres(){

		   List<SelectItem> items = new ArrayList<SelectItem>();
		   List<User> categoryList = es.getAllUsers();
		    for(User emp: categoryList){
		       items.add(new SelectItem(emp.getId(), emp.getNom()));
		   }
		   return items;
		}



}
