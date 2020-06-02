package com.ktu.ld1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.firestore.DocumentSnapshot;

class MyViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;

    TextView event_name,event_rating;
    ImageView event_image;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        event_name = itemView.findViewById(R.id.event_name);
        event_rating = itemView.findViewById(R.id.event_rating);
        event_image = itemView.findViewById(R.id.event_image);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION && listener != null){
                   // listener.onItemClick();
                }

            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }

}


