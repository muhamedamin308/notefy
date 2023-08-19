package com.example.recycleview.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.recycleview.db.Notebook;
import com.example.recycleview.db.NotebookRepository;

public class AddNotebookViewModel extends AndroidViewModel {
    private NotebookRepository mNotebookRepository;

    public AddNotebookViewModel(@NonNull Application application) {
        super(application);
        mNotebookRepository = new NotebookRepository(application);

    }

    public void insertAV (Notebook notebook){
        mNotebookRepository.insertR(notebook);
    }
    public void updateAV (Notebook notebook){
        mNotebookRepository.updateR(notebook);
    }

}
