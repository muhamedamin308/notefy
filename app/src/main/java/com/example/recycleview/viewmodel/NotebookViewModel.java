package com.example.recycleview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.recycleview.db.Notebook;
import com.example.recycleview.db.NotebookRepository;

import java.util.List;

public class NotebookViewModel extends AndroidViewModel {
    private final NotebookRepository notebookRepository;
    private final LiveData<List<Notebook>> getAllNotesVM;
    public NotebookViewModel(@NonNull Application application) {
        super(application);
        notebookRepository = new NotebookRepository(application);
        getAllNotesVM = notebookRepository.getAllNotesRepo();
    }
    public void deleteV (Notebook notebook){
        notebookRepository.deleteRepo(notebook);
    }
    public LiveData<List<Notebook>> getAllData(){
        return getAllNotesVM;
    }
}
