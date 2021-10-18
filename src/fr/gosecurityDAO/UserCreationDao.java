package fr.gosecurityDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import fr.gosecuri.pojos.Materiel;
import fr.gosecuri.pojos.Mouvement;
import fr.gosecuri.pojos.User;

public class UserCreationDao {
	
	private Firestore db;


	public UserCreationDao(Firestore db) {


		//connexion ) la bdd firestore
		try {
			this.db = db;
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public User getUser(String id) throws InterruptedException, ExecutionException {
		User user = null;
		
		DocumentReference docRef = db.collection("Users").document(id);
		
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document;
		
		document = future.get();
		
		if (document.exists()) {
		 // convert document to POJO
		 user = document.toObject(User.class);
		 user.setLogin(document.getId());
		} 

		return user;
	}

	public void insert(User user) {
		DocumentReference docRef = db.collection("Users").document(user.getLogin());

		Map<String, Object> data = new HashMap<>();
		data.put("nom", user.getNom());
		data.put("prenom", user.getPrenom());
		data.put("login", user.getLogin());
		data.put("password", user.getPassword());
		data.put("dob", user.getDob());
		data.put("photo", user.getPhoto());
		data.put("IsAdmin", user.getIsAdmin());

		docRef.set(data);
		

	}


	public List<User> getUsers(String login) throws InterruptedException, ExecutionException {

		List<User> users = new ArrayList<User>();
		
		CollectionReference userRef = db.collection("Users");
		
		Query query = userRef.whereEqualTo("login", login);
		
		// retrieve  query results asynchronously using query.get()
		ApiFuture<QuerySnapshot> querySnapshot = query.get();

		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {

			User user = new User();

			user.setLogin(document.getString("login"));
			user.setNom(document.getString("nom"));
			user.setPrenom(document.getString("prenom"));
			user.setPassword(document.getString("password"));
			user.setDob(document.getDate("dob"));
			user.setPhoto(document.getString("photo"));
			user.setIsAdmin(document.getBoolean("IsAdmin"));

			users.add(user);
		}

		return users;
	}
	
	public void addUser(User u) {
		//ajoute l'utilisateur u à firebase
		ApiFuture<WriteResult> future = db.collection("users").document(Integer.toString(u.getId())).set(u);
	
	}
	public List<Integer> getUserIdList()
	{
		List<Integer> userIdList = new ArrayList<Integer>();
		try {
			// Envoi de la requête à Firebase
			ApiFuture<QuerySnapshot> future =db.collection("users").get();

			// Recupère la liste des documents
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			for (DocumentSnapshot document : documents) {

				userIdList.add(document.toObject(User.class).getId());
				

				//userIdList.add(document.toObject(User.class).getUser_id());
				//System.out.println(document.getId() + " => " + document.toObject(Material.class));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return userIdList;

	}
	public void createNewUser(User u)
	{
		List<Integer> userIdList = new ArrayList<Integer>();
		userIdList=this.getUserIdList();

		Collections.sort(userIdList);

		if(userIdList.isEmpty()==false)
		{
			int newId=(int) (userIdList.get(userIdList.size()-1))+1;
			u.setId(newId);
		}
		else
		{
			int newId=1;
			u.setId(newId);
		}

		this.addUser(u);

	}


}
