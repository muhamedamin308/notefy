package com.example.recycleview.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotebookRepository {
    private final NotebookDao notebookDao;
    private final LiveData<List<Notebook>> getAllNotes;

    public NotebookRepository(Application application) {
        NotebookRoomDB db = NotebookRoomDB.getInstance(application);
        notebookDao = db.notebookDao();
        getAllNotes = notebookDao.getAllNotebooks();
    }

    public void insertRepo(Notebook notebook) {
        new InsertAsyncTask(notebookDao).execute(notebook);
    }

    public void updateRepo(Notebook notebook) {
        new UpdateAsyncTask(notebookDao).execute(notebook);
    }

    public void deleteRepo(Notebook notebook) {
        new DeleteAsyncTask(notebookDao).execute(notebook);
    }

    public LiveData<List<Notebook>> getAllNotesRepo() {
        return getAllNotes;
    }


    private static class InsertAsyncTask extends AsyncTask<Notebook, Void, Void> {
        private final NotebookDao notebookDao1;
        public InsertAsyncTask(NotebookDao notebookDao) {
            notebookDao1 = notebookDao;
        }
        @Override
        protected Void doInBackground(Notebook... notebooks) {
            notebookDao1.insert(notebooks[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Notebook, Void, Void> {
        private final NotebookDao notebookDao1;

        public UpdateAsyncTask(NotebookDao notebookDao) {
            notebookDao1 = notebookDao;
        }

        @Override
        protected Void doInBackground(Notebook... notebooks) {
            notebookDao1.update(notebooks[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Notebook, Void, Void> {
        private final NotebookDao notebookDao1;

        public DeleteAsyncTask(NotebookDao notebookDao) {
            notebookDao1 = notebookDao;
        }

        @Override
        protected Void doInBackground(Notebook... notebooks) {
            notebookDao1.delete(notebooks[0]);
            return null;
        }
    }
}
