package fr.gosecuri.pojos;

public class Materiel {
	String id;
	String nom;
	Long qteEnStock;
	
	


	public Materiel() {
		super();
	}


	public Materiel(String id, String nom, Long qteEnStock) {
		super();
		this.id = id;
		this.nom = nom;
		this.qteEnStock = qteEnStock;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Long getQteEnStock() {
		return qteEnStock;
	}


	public void setQteEnStock(Long qteEnStock) {
		this.qteEnStock = qteEnStock;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "Materiel [id=" + id + ", nom=" + nom + ", QteEnStock=" + qteEnStock + "]";
	}
	
	
	
	
	
}
