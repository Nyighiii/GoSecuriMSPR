package fr.gosecurityDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import fr.gosecuri.pojos.Materiel;
import fr.gosecuri.pojos.Mouvement;

public class MouvementDao {

	private Firestore db;


	public MouvementDao(Firestore db) {


		//connexion ) la bdd firestore
		try {
			this.db = db;
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insert(Mouvement mouvement) {
		DocumentReference docRef = db.collection("Mouvements").document();

		Map<String, Object> data = new HashMap<>();
		data.put("idMateriel", mouvement.getIdMateriel());
		data.put("login", mouvement.getLogin());
		data.put("date", mouvement.getDate());
		data.put("IsBorowed", mouvement.getIsBorowed());

		docRef.set(data);
	}


	public List<Mouvement> getMouvements(String login) throws InterruptedException, ExecutionException {

		List<Mouvement> mouvements = new ArrayList<Mouvement>();
		
		CollectionReference mouvRef = db.collection("Mouvements");
		
		Query query = mouvRef.whereEqualTo("login", login);
		
		// retrieve  query results asynchronously using query.get()
		ApiFuture<QuerySnapshot> querySnapshot = query.get();

		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

			Mouvement mouvement = new Mouvement();

			mouvement.setId(document.getId());
			mouvement.setDate(document.getDate("CurrentDate"));
			mouvement.setLogin(document.getString("login"));
			mouvement.setIdMateriel(document.getString("idMateriel"));
			mouvement.setIsBorowed(document.getBoolean("IsBorowed"));

			mouvements.add(mouvement);
		}

		return mouvements;
	}



}
