package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class NotebookDetails extends AppCompatActivity {
    TextView title, description, longDescription;
    CardView priority;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_details);
        priority = findViewById(R.id.priorityDetails);
        title = findViewById(R.id.notebookTitle);
        description = findViewById(R.id.notebookDetails);
        longDescription = findViewById(R.id.notebookDetails2);
        Intent intent = getIntent();

        String Title = Objects.requireNonNull(intent.getExtras()).getString("title");
        String description1 = intent.getExtras().getString("description");
        String description2 =intent.getExtras().getString("longDescription");
        int notebookPriority = intent.getExtras().getInt("priority");

        title.setText(Title);
        description.setText(description1);
        longDescription.setText(description2);
        priority.setCardBackgroundColor(getResources().getColor(notebookPriority));

        findViewById(R.id.imageBack).setOnClickListener(view -> finish());
    }
}