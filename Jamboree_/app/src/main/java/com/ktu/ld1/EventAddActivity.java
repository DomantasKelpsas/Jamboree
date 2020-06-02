package com.ktu.ld1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EventAddActivity extends AppCompatActivity {

    EditText eventNameAdd;
    Firebase firebase;

    Button btnImageChoose, btnEventAdd;
    ImageView eventImage;

    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        firebase = new Firebase();

        eventNameAdd = findViewById(R.id.event_name_add);
        btnImageChoose = findViewById(R.id.btn_image_choose);
        btnEventAdd = findViewById(R.id.btn_event_add);
        eventImage = findViewById(R.id.event_image_add);

        btnImageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Filechooser();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            eventImage.setImageURI(imguri);
        }
    }

    private void Filechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    private void Fileuploader(String filename) {

        StorageReference myStorageRef = firebase.myStorageRef.child(filename);  // + "." + getExtension(imguri)

        UploadTask uploadTask = myStorageRef.putFile(imguri);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Toast.makeText(EventAddActivity.this, "image has been uploaded", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }


    public void btnClick(View v) {

        Intent intent = null;

        if (v.getId() == R.id.btn_save_add) {

            String filename = System.currentTimeMillis() + "";

            Fileuploader(filename);


            String eventImage = filename;
            String eventName = eventNameAdd.getText().toString();

            firebase.AddEvent(eventName, "5.0", eventImage);


        }


    }
}
