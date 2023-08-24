package com.example.recycleview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recycleview.db.Notebook;
import com.example.recycleview.db.NotebookRepository;

public class AddNotebookViewModel extends AndroidViewModel {
    private final NotebookRepository notebookRepository;

    public AddNotebookViewModel(@NonNull Application application) {
        super(application);
        notebookRepository = new NotebookRepository(application);
    }
    public void insertAN(Notebook notebook){
        notebookRepository.insertRepo(notebook);
    }
    public void updateVM(Notebook notebook){
        notebookRepository.updateRepo(notebook);
    }

}
