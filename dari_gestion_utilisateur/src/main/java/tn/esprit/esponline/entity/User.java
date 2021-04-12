package tn.esprit.esponline.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Useres")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String prenom;
	@Column
	private String nom;
	@Column
	private String email;
	@Column
	private String password;
	

	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	
	/*private List<TimeSheet> timeSheets;

	@OneToMany(mappedBy = "employe")
	public List<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(List<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}*/

	public User(){}
	
	
	
	public User(String nom, String prenom, String email, String password, Role role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.role = role;
	}



	public User(long id, String prenom, String nom, String email, String
			password, Role role) {
			super();
			this.id = id;
			this.prenom = prenom;
			this.nom = nom;
			this.email = email;
			this.password = password;
			this.role = role;
			}

	


	public User(Long id2, String nom2) {
		this.id = id2;
		this.nom = nom2;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return prenom;
	}


	
	

}
