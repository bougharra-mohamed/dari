package tn.esprit.esponline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.esponline.entity.User;


public interface IUserRepository extends JpaRepository<User, Long> {
	
	 @Query("Select count(*) From User e")
	 int getNombreUser();
	
	 @Query("Select e.nom  from User e ")
	 List<String> getUserNames();
	 
	 @Query("SELECT e FROM User e WHERE e.email=:email and e.password=:password")
	 public User getUserByEmailAndPassword(@Param("email")String login,
	 @Param("password")String password);
	 

}
