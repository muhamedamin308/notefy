package com.example.recycleview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recycleview.db.Notebook;
import com.example.recycleview.db.NotebookRepository;

import java.util.List;

public class NotebookViewModel extends AndroidViewModel {
    private NotebookRepository mNotebookRepository;
    private LiveData<List<Notebook>> mAllData;
    public NotebookViewModel(@NonNull Application application) {
        super(application);
        mNotebookRepository = new NotebookRepository(application);
        mAllData = mNotebookRepository.getAlldata();
    }

    public void insertV (Notebook notebook){
        mNotebookRepository.insertR(notebook);
    }
    public void updateV (Notebook notebook){
        mNotebookRepository.updateR(notebook);
    }
    public void deleteV (Notebook notebook){
        mNotebookRepository.deleteR(notebook);
    }
    public void deleteAllPlanets (){
        mNotebookRepository.deleteAllPlanets();
    }
    public LiveData<List<Notebook>> getAllData(){
        return mAllData;
    }
}
