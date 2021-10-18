package fr.gosecurityDAO;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

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

	public boolean read(String login, String password) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> loginQuery = db.collection("Users").whereEqualTo("Login", login)
				.whereEqualTo("Password", password).get();
		QuerySnapshot querySnapshot = loginQuery.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		if (documents.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public User getUser(String login, String password) throws InterruptedException, ExecutionException {
		/* ApiFuture<QuerySnapshot> loginQuery = db.collection("Users").whereEqualTo("Login", login)
				.whereEqualTo("Password", password).get();*/
		DocumentReference documentReference = db.collection("Users").document(login);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();
		
		User user = null;
		
		/*QuerySnapshot querySnapshot = loginQuery.get();
		User user = new User();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();*/
		if (document.exists()) {
			 user = document.toObject(User.class);
			 if (user.getPassword().equals(password)) {
				return user;
			} else {
				
				return null;
			}
			
		} else {
			
			return null;
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
