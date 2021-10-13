package fr.gosecuri.pojos;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;


public class User {
	int id;
	String nom;
	String prenom;
	String login;
	String password;
	Date dob;
	String photo;
	Boolean IsAdmin;
	
	
	public User(String nom, String prenom, String login, String password,Date dob, String photo, Boolean isAdmin) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.dob = dob;
		this.photo = photo;
		IsAdmin = isAdmin;
		
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	public User() {
	
	}



	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Boolean getIsAdmin() {
		return IsAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		IsAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User []";
	}
	
	
	
}
