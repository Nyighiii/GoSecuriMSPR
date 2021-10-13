package fr.gosecurityDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import fr.gosecuri.pojos.Materiel;

public class MaterielDao {

	private Firestore db;

	public MaterielDao(Firestore db) {

		//connexion ) la bdd firestore
		try {
			this.db = db;
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Materiel getMateriel(String id) throws InterruptedException, ExecutionException {
		Materiel materiel = null;
		
		DocumentReference docRef = db.collection("Materiels").document(id);
		
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document;
		
		document = future.get();
		
		if (document.exists()) {
		 // convert document to POJO
		 materiel = document.toObject(Materiel.class);
		 materiel.setId(document.getId());
		} 

		return materiel;
		
	}

	public List<Materiel> getMateriels() throws InterruptedException, ExecutionException {

		List<Materiel> materiels = new ArrayList<Materiel>();

		ApiFuture<QuerySnapshot> query = db.collection("Materiels").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();


		for (QueryDocumentSnapshot document : documents) {

			Materiel materiel = new Materiel();

			materiel.setId(document.getId());
			materiel.setNom(document.getString("nom"));
			materiel.setQteEnStock(document.getLong("qteEnStock"));


			materiels.add(materiel);

		}

		return materiels;		
	}

	public void insert(Materiel materiel) {
		DocumentReference docRef = db.collection("Materiels").document(materiel.getId());

		Map<String, Object> data = new HashMap<>();
		data.put("qteEnStock", materiel.getQteEnStock());
		data.put("nom", materiel.getNom());


		docRef.set(data);
	}


}

