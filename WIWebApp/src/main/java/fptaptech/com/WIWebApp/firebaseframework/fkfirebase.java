package fptaptech.com.WIWebApp.firebaseframework;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author CHIEN
 * @param <T>
 */
public final class fkfirebase<T> {

    //private T t;
    private Class Tclass;
    private String Nclass;

    //private Firestore dbFirestore = FirestoreClient.getFirestore();
    public fkfirebase(T t) {
        try {
            //this.t = t;
            //dbFirestore = FirestoreClient.getFirestore();
            Nclass = t.getClass().getSimpleName();
            Tclass = Class.forName(t.getClass().getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(fkfirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public Class returnClass() {
//        try {
//            String name = t.getClass().getName();
//            return Class.forName("com.example.firebase.model.User");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(fkfirebase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public Object test() throws ClassNotFoundException {

        //Object a = Class.forName(t.getClass().getName().toString()).cast(t);
        return null;
    }

    public String create(Object newob) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(Nclass).document().set(newob);
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(fkfirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }

    public List<T> findAll() {

        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            List<QueryDocumentSnapshot> lists = dbFirestore.collection(Nclass).get().get().getDocuments();
            List<T> list = new ArrayList<>();
            lists.forEach(document -> {
                list.add((T) document.toObject(Tclass));
            });
            return list;

        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(fkfirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public T getCRUD(String documentID) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(Nclass).document(documentID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            T ob = (T) document.toObject(Tclass);
            return ob;
        }
        return null;
    }

    public String updateCRUD(Object object, String documentId) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(Nclass).document(documentId).set(object);
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(fkfirebase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fail";
    }

    public String deleteCRUD(String documentID) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection(Nclass).document(documentID).delete();
        return "Successfully delete" + documentID;
    }

    public T getUserbymail(String mail) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference users = dbFirestore.collection(Nclass);
        Query query = users.whereEqualTo("email", mail);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        DocumentSnapshot document = documents.get(0);
        if (document.exists()) {
            T ob = (T) document.toObject(Tclass);
            return ob;
        }
        return null;
    }
}
