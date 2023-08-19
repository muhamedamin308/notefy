package com.example.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycleview.db.Notebook;
import com.example.recycleview.viewmodel.AddNotebookViewModel;

import java.util.Objects;

public class AddNotebook extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText notebookTitle, notebookDesc1, notebookDesc2;
    private String selectedPriority ;
    private AddNotebookViewModel mViewModel;
    private int mID, priority1;
    private boolean editMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notebook);

        notebookTitle = findViewById(R.id.newNotebookTitle);
        notebookDesc1 = findViewById(R.id.newNotebookDesc1);
        notebookDesc2 = findViewById(R.id.newNotebookDesc2);
        Spinner priority = findViewById(R.id.newNotebookPriority);
        TextView notebookPriority = findViewById(R.id.addNotebookPriority);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        Intent i = getIntent();
        if (i.hasExtra("ID")){
            setTitle("Notebook Update");
            editMode = true;
            mID = i.getIntExtra("ID" , -1);
            notebookTitle.setText(i.getStringExtra("TITLE"));
            notebookDesc1.setText(i.getStringExtra("DESC1"));
            notebookDesc2.setText(i.getStringExtra("DESC2"));
            priority1 = Objects.requireNonNull(i.getExtras()).getInt("PRIORITY");
            notebookPriority.setBackgroundColor(getResources().getColor(priority1));

        }else{
            setTitle("Add New Notebook");
            editMode = false;
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priorities, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        priority.setAdapter(adapter);
        priority.setOnItemSelectedListener(this);
        mViewModel = ViewModelProviders.of(this).get(AddNotebookViewModel.class);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu1 , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.saveItem) {
            savePlanet();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void savePlanet(){

        String notebookName = notebookTitle.getText().toString();
        String notebookDesc1 = this.notebookDesc1.getText().toString();
        String notebookDesc2 = this.notebookDesc2.getText().toString();
        int priorities = 0;
        switch (selectedPriority) {
            case "Low" :
                priorities = R.color.low_priority;
                priority1 = priorities;
                break;
            case "Medium" :
                priorities = R.color.medium_priority;
                priority1 = priorities;
                break;
            case "High" :
                priorities = R.color.high_priority;
                priority1 = priorities;
                break;
            case "Default":
                priorities = R.color.default_priority;
                priority1 = priorities;
                break;
        }
        if (notebookName.isEmpty() || notebookDesc1.isEmpty() || notebookDesc2.isEmpty()){
            Toast.makeText(AddNotebook.this , "Please Fill All Fields!!" , Toast.LENGTH_LONG).show();
            return ;
        }
        if (editMode){
            Notebook notebook = new Notebook(priority1 , notebookName , notebookDesc1 , notebookDesc2);
            notebook.setNumber(mID);
            mViewModel.updateAV(notebook);
        }else{
            mViewModel.insertAV(new Notebook(priorities , notebookName , notebookDesc1 , notebookDesc2));
        }
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedPriority = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}