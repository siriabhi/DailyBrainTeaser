package com.example.dailybrainteaser;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen1 extends AppCompatActivity {

    Button tech1,science1,health1,vocab1,gk1,puzzles1,quotes1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        tech1 = findViewById(R.id.tech);
        science1 = findViewById(R.id.science);
        health1 = findViewById(R.id.health);
        vocab1 = findViewById(R.id.vocab);
        gk1 = findViewById(R.id.gk);
        puzzles1 = findViewById(R.id.puzzles);
        quotes1 = findViewById(R.id.Quotes);

        tech1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Tech.class);
                startActivity(i1);
            }
        });
        science1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Science.class);
                startActivity(i1);
            }
        });
        health1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Health.class);
                startActivity(i1);
            }
        });
        vocab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Vocabulary.class);
                startActivity(i1);
            }
        });
        gk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,General.class);
                startActivity(i1);
            }
        });
        puzzles1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Puzzles.class);
                startActivity(i1);
            }
        });
        quotes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(screen1.this,Quotations.class);
                startActivity(i1);
            }
        });

    }
}