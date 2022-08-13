package com.example.mobile.fkfirebase;

import androidx.annotation.NonNull;

import com.example.mobile.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FkFireBase<T> {
    private DatabaseReference databaseReference;
    private FirebaseFirestore firebaseFirestore;
    private Class Tclass;
    private String Nclass;
    private T t;

    public FkFireBase(T t) {
        try {
            Nclass = t.getClass().getSimpleName();
            Tclass = Class.forName(t.getClass().getName());
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference(Nclass);
            firebaseFirestore = FirebaseFirestore.getInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FkFireBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(T t) {
        firebaseFirestore.collection(Nclass).document()
                .set(t)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        return;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public void delete(String documentId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(Nclass)
                .document(documentId)
                .delete();
    }

    //    public List<T> findAll(){
//        List<T> list= new ArrayList<>();
//        DocumentReference docRef = firebaseFirestore.collection(Nclass).document();
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
////                    task.getResult().forEach(document -> {
////                        list.add((T) document.toObject(Tclass));
////                    });
//                    for (QueryDocumentSnapshot document : task.getResult().getData()) {
//                        T t = (T) document.toObject(Tclass);
//                        list.add(t);
//                    }
//                }
//            }
//        });
//        return list;
//    }
    //get by doc id
    public T getDocId(String docId) {
        DocumentReference docRef = firebaseFirestore.collection(Nclass).document(docId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    t = (T) document.toObject(Tclass);

                }
            }
        });
        return t;
    }

    public void update(T t, String docId) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection(Nclass).document(docId)
                .set(t)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        firebaseFirestore.collection(Nclass).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    task.getResult().forEach(document -> {
                        list.add((T) document.toObject(Tclass));
                    });
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        User user = document.toObject(User.class);
//                        list.add(user);
//                    }
                }
            }
        });
        return list;
    }

    //    public List<T> findWhereEqualTo(String fieldName, Object value){
//        List<T> list= new ArrayList<>();
//        DocumentReference docRef1 = firebaseFirestore.collection(Nclass).document();
//        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
////                    task.getResult().forEach(document -> {
////                        list.add((T) document.toObject(Tclass));
////                    });
//                    for (DocumentSnapshot document : task) {
//                        User user = document.toObject(User.class);
//                        list.add(user);
////                    }
//                }
//            }
//        });
//        return list;
//    }
//    public void test(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef = db.collection("User").document();
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (DocumentSnapshot documentSnapshot : task)
//                        DocumentSnapshot document = task.getResult();
//                        user = document.toObject(User.class);
//                }
//            }
//        });
//        DocumentReference docRef1 = firebaseFirestore.collection("User").document();
//        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    user = document.toObject(User.class);
//                }
//            }
//        });
    public List<T> findWhereEqualTo(String fieldName, Object value) {
        List<T> list = new ArrayList<>();
        firebaseFirestore.collection(Nclass)
                .whereEqualTo( fieldName, value)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add((T) document.toObject(Tclass));
                            }
                        } else {

                        }
                    }
                });
        return list;
    }
}
//        DocumentReference docRef = firebaseFirestore.collection("User").document("OH51Z62oueYF2Xr1j02n");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    user = document.toObject(User.class);
//                }
//            }
//        });
//public List<T> findWhereEqualTo(String fieldName, Object value){
//    List<T> list= new ArrayList<>();
//    DocumentReference docRef1 = firebaseFirestore.collection(Nclass).document();
//    docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//            if (task.isSuccessful()) {
////                    task.getResult().forEach(document -> {
////                        list.add((T) document.toObject(Tclass));
////                    });
//                for (DocumentSnapshot document : task) {
//                    User user = document.toObject(User.class);
//                    list.add(user);
////                    }
//                }
//            }
//        });
//        return list;
//    }
