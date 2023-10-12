package com.example.dailybrainteaser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.dailybrainteaser.DisplayText;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Tech extends AppCompatActivity {
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech);
//        DisplayText text = new DisplayText();
//        text.display();
//        Log.d("text received","success");
//        TextView tech_text = findViewById(R.id.tech_text);
//        tech_text.setText(text.text1);
        showText();
        showImage();

    }

    private void showImage() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("Tech/"+currentDate+".jpg");
        imageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                ImageView image = findViewById(R.id.tech_img);
                image.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Tech.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showText() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TextView textView2 = findViewById(R.id.tech_text);
        db = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data");
        reference.child("Tech").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(Tech.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot ds = task.getResult();
                        String name1 = String.valueOf(ds.child(currentDate).getValue());
                        textView2.setText(name1);

                    }else{
                        Toast.makeText(Tech.this, "Data Doesn't exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Tech.this, "failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}