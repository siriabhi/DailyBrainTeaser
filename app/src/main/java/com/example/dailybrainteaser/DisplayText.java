package com.example.dailybrainteaser;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class DisplayText {
    public String text1;
    public void display(){
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tech");
        reference.child("Data").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot ds = task.getResult();
                        text1 = String.valueOf(ds.child(currentDate).getValue());
                        Log.d("received data","read successfully");
                    } else {
                        Log.d("data", "Data Doesn't exists");
                    }

                } else {
                    Log.d("read", "Failed to read");
                }

            }
    });
}
}
