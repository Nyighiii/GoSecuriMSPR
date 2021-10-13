package fr.gosecurityDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FireBaseDao {
	
	private Firestore db;
	
	
	//constructeur de classe
	public FireBaseDao (String contextPath)	{

	
	FileInputStream serviceAccount;
	
			FirebaseOptions options = null;
			
			try {
				
				serviceAccount = new FileInputStream(contextPath + "/key.json");

				options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			
			FirebaseApp.initializeApp(options);
			
	}
	
	public Firestore getDbConnexion() {
		if(this.db == null) {
			this.db = FirestoreClient.getFirestore();
		}
		
		return this.db;
	}
}
