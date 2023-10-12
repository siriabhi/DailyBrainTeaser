package com.example.dailybrainteaser;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
public class Puzzles extends AppCompatActivity {
    EditText solutions;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzles);
        solutions = findViewById(R.id.solution);
        submit = findViewById(R.id.submit);
        showImage();
        solutions();
//        ShowImage myImage = new ShowImage();
//        myImage.displayImage();
//        Bitmap mybitmap = myImage.Bitmapimg;
//        ImageView image1 = findViewById(R.id.puzzle_img);
//        image1.setImageBitmap(mybitmap);
//        Toast.makeText(this, "image received", Toast.LENGTH_SHORT).show();
    }

    private void solutions() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        HashMap<String, String> map=new HashMap<>();
        map.put("2023-04-29","1");
        map.put("2023-04-30","10");
        map.put("2023-05-01","*+*");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = solutions.getText().toString();
                Log.d("value",ans);
                Log.d("answer", String.valueOf(ans.equals(map.get(currentDate))));
                if (ans.equals(map.get(currentDate))){
                    Toast.makeText(Puzzles.this, "Good! You are correct", Toast.LENGTH_SHORT).show();
                    solutions.setText(" ");
                }
                else{
                    solutions.setText(" ");
                    Toast.makeText(Puzzles.this, "Incorrect answer! Please try again", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void showImage() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("puzzles/"+currentDate+".jpg");
        imageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
                ImageView image = findViewById(R.id.puzzle_img);
                image.setImageBitmap(bitmap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Puzzles.this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
