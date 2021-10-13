package fr.gosecurityDAO;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import fr.gosecuri.pojos.User;

public class UserDao {
	
	private Firestore db;
	
	public UserDao(Firestore db) {
		
		//connexion ) la bdd firestore
		try {
			this.db = db;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public User getUserByLoginPassword(String login) {
		
		User u = new User();
		
		DocumentReference docRef = db.collection("Users").document(login);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		
	
		
		DocumentSnapshot document;
		try {
			document = future.get();
			
			if (document.exists()) 
				u = document.toObject(User.class);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return u;

	}
	
	
}
