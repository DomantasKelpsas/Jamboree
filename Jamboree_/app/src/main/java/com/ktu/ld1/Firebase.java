package com.ktu.ld1;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Firebase {

    DatabaseReference myRef;
    StorageReference myStorageRef;
    String id;

    public Firebase() {

        myRef = FirebaseDatabase.getInstance().getReference();
        myStorageRef = FirebaseStorage.getInstance().getReference("event");
    }

    public Firebase(String refPath) {

        myRef = FirebaseDatabase.getInstance().getReference(refPath);
        myStorageRef = FirebaseStorage.getInstance().getReference(refPath);
    }


    public void AddEvent(String eventName, String eventRating, String eventImage) {

        DatabaseReference eventRef = myRef.child("event");
        id = eventRef.push().getKey();

        eventRef.child(id).child("eventName").setValue(eventName);
        eventRef.child(id).child("eventRating").setValue(eventRating);
        eventRef.child(id).child("eventImage").setValue(eventImage);
    }

    public String getEventImageUri(String filename) {


      return "";
    }


//        myRef.addValueEventListener(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(DataSnapshot dataSnapshot) {
//                                            ArrayList<String> tempArray = new ArrayList<>();
//
//                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                                String evName = ds.child("eventName").getValue().toString();
//
//                                                tempArray.add(evName);
//
//                                            }
//
//                                        }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
}
