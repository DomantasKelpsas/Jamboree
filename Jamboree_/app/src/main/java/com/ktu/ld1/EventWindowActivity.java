package com.ktu.ld1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventWindowActivity extends AppCompatActivity {


    RecyclerView eventRecyclerView;
    Firebase firebase;

    DatabaseReference myRef;
    StorageReference myStorageRef;

    String imageUri;


    private FirebaseRecyclerOptions<EventModel> options;
    private FirebaseRecyclerAdapter<EventModel, MyViewHolder> adapter;

    final String TAG = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_window);

        firebase = new Firebase("event");

        myRef = firebase.myRef;
        myStorageRef = firebase.myStorageRef;

        //imageUri= "";

        eventRecyclerView = findViewById(R.id.event_recyclerView);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        options = new FirebaseRecyclerOptions.Builder<EventModel>().setQuery(myRef, EventModel.class).build();
        adapter = new FirebaseRecyclerAdapter<EventModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyViewHolder holder, final int position, @NonNull EventModel model) {

                final ImageView imageView = holder.event_image;


                myStorageRef.child(model.getEventImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUri = uri.toString();
                        Picasso.get().load(uri.toString()).into(holder.event_image);
                    }
                });


                //  Picasso.get().load(model.getEventImage(myStorageRef)).into(holder.event_image);
                holder.event_name.setText(model.getEventName());
                holder.event_rating.setText(model.getEventRating());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String eventId = getRef(position).getKey();
                        Intent intent = new Intent(EventWindowActivity.this, EventSingleActivity.class);
                        intent.putExtra("eventId", eventId);

                        Toast.makeText(EventWindowActivity.this, "Event ID --> " + eventId, Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View view = inflater.inflate(R.layout.event_row, parent, false);
                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        eventRecyclerView.setAdapter(adapter);
    }


    public void btnClick(View v) {
        Intent intent = null;

        if (v.getId() == R.id.btn_settings)
            intent = new Intent(this, SettingsActivity.class);
        else if (v.getId() == R.id.btn_user)
            intent = new Intent(this, UserActivity.class);
        else if (v.getId() == R.id.btn_event_add)
            intent = new Intent(this, EventAddActivity.class);


        if (intent != null)
            startActivity(intent);


    }


}
