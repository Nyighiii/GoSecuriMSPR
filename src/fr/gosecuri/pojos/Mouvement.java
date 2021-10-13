package fr.gosecuri.pojos;

import java.util.Date;

public class Mouvement {
	
	String login;
	String id;
	Date date;
	String idMateriel;
	Boolean IsBorowed;
	
	public Mouvement() {
		super();
	}




	public Mouvement(String login, String id, Date date, String idMateriel, Boolean isBorowed) {
		super();
		this.login = login;
		this.id = id;
		this.date = date;
		this.idMateriel = idMateriel;
		IsBorowed = isBorowed;
	}




	public Boolean getIsBorowed() {
		return IsBorowed;
	}


	public void setIsBorowed(Boolean isBorowed) {
		IsBorowed = isBorowed;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getIdMateriel() {
		return idMateriel;
	}


	public void setIdMateriel(String idMateriel) {
		this.idMateriel = idMateriel;
	}
	
	
	
	
}

