package com.example.dailybrainteaser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Vocabulary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        showText();
    }
    private void showText() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        TextView textView2 = findViewById(R.id.vocab_text);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data");
        reference.child("Vocabulary").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(Vocabulary.this, "Successfully read", Toast.LENGTH_SHORT).show();
                        DataSnapshot ds = task.getResult();
                        String name1 = String.valueOf(ds.child(currentDate).getValue());
                        textView2.setText(name1);

                    }else{
                        Toast.makeText(Vocabulary.this, "Data Doesn't exists", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Vocabulary.this, "failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}