package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.recycleview.adapter.NotebookAdapter;
import com.example.recycleview.viewmodel.NotebookViewModel;

public class MainActivity extends AppCompatActivity  {
    private NotebookViewModel mNotebookViewModel;
    private NotebookAdapter adapter;
    private ConstraintLayout emptyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("THE NOTEBOOK");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        emptyList = findViewById(R.id.addNote);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new NotebookAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mNotebookViewModel = ViewModelProviders.of(this).get(NotebookViewModel.class);
        mNotebookViewModel.getAllData().observe(this, notebooks -> {
            if (notebooks.isEmpty()) {
                recyclerView.setVisibility(View.INVISIBLE);
                emptyList.setVisibility(View.VISIBLE);
                emptyList.setOnClickListener(view -> {
                    Intent intent = new Intent(MainActivity.this, AddNotebook.class);
                    startActivity(intent);
                });
            }else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyList.setVisibility(View.INVISIBLE);
                adapter.initializeNotebooks(notebooks);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                mNotebookViewModel.deleteV(adapter.getNotebookAt(pos));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu2 , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addItem) {
            Intent intent = new Intent(MainActivity.this, AddNotebook.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}