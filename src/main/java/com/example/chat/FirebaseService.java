package com.example.chat;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {

	public String createCRUD(Models crud) throws InterruptedException, ExecutionException {

		Firestore dbFirestore=FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture=dbFirestore.collection("crud_user").document(crud.getName()).set(crud);
		
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public Models getCRUD(String documentId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore=FirestoreClient.getFirestore();
		DocumentReference documentReference=dbFirestore.collection("crud_user").document(documentId);
		ApiFuture<DocumentSnapshot> future=documentReference.get();
		DocumentSnapshot document=future.get();
		
		Models crud;
		if(document.exists()) {
			crud=document.toObject(Models.class);
			return crud;
		}
		return null;
	}

	public String updateCRUD(Models crud) throws InterruptedException, ExecutionException {
		Firestore dbFirestore=FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture=dbFirestore.collection("crud_user").document(crud.getName()).set(crud);

		return collectionApiFuture.get().getUpdateTime().toString();
	}

	public String deleteCRUD(String documentId) {
		Firestore dbFirestore=FirestoreClient.getFirestore();
		dbFirestore.collection("crud_user").document(documentId).delete();

		return "Successfully deleted" + documentId;
	}
	
}
