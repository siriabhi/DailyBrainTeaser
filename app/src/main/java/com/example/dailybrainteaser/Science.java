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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Science extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference reference;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        showText();
        showImage();

    }
    private void showImage() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("Science/"+currentDate+".jpg");
        imageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                ImageView image = findViewById(R.id.science_img);
                image.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Science.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showText() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.d("today's date",currentDate);
        textView3 = findViewById(R.id.science_text);
        db = FirebaseDatabase.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Data");
        reference.child("Science").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(Science.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot ds = task.getResult();
                        String name1 = String.valueOf(ds.child(currentDate).getValue());
                        textView3.setText(name1);

                    }else{
                        Toast.makeText(Science.this, "Data Doesn't exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Science.this, "failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}