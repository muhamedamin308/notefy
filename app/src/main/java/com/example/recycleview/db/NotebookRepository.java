package com.example.recycleview.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotebookRepository {
    private NotebookDao mNotebookDao;
    private LiveData<List<Notebook>> getAllNotes;

    public NotebookRepository(Application application){
        NotebookRoomDB db = NotebookRoomDB.getInstance(application);
        mNotebookDao = db.notebookDao();
        getAllNotes = mNotebookDao.getAllNotebooks();
    }

    //Operations


    //Insert
        public void insertR (Notebook notebook){
            new InsertAsyncTask(mNotebookDao).execute(notebook);
        }
    //Update
        public void updateR (Notebook notebook){
            new UpdateAsyncTask(mNotebookDao).execute(notebook);
        }
    //Delete
        public void deleteR (Notebook notebook){
            new DeleteAsyncTask(mNotebookDao).execute(notebook);
        }
    //Delete All
        public void deleteAllPlanets (){
            new DeleteAllAsyncTask(mNotebookDao).execute();
        }
    //Get All Data
        public LiveData<List<Notebook>> getAlldata(){
            return getAllNotes;
        }



    private static class InsertAsyncTask extends AsyncTask<Notebook, Void , Void>{
        private NotebookDao mNotebookDao;

        public InsertAsyncTask(NotebookDao notebookDao){
            mNotebookDao = notebookDao;
        }
        @Override
        protected Void doInBackground(Notebook... notebooks) {
            mNotebookDao.insert(notebooks[0]);

            return null;
        }
    }


    private static class UpdateAsyncTask extends AsyncTask<Notebook, Void , Void>{
        private NotebookDao mNotebookDao;

        public UpdateAsyncTask(NotebookDao notebookDao){
            mNotebookDao = notebookDao;
        }
        @Override
        protected Void doInBackground(Notebook... notebooks) {
            mNotebookDao.update(notebooks[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Notebook, Void , Void>{
        private NotebookDao mNotebookDao;

        public DeleteAsyncTask(NotebookDao notebookDao){
            mNotebookDao = notebookDao;
        }
        @Override
        protected Void doInBackground(Notebook... notebooks) {
            mNotebookDao.delete(notebooks[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Void , Void , Void>{
        private NotebookDao mNotebookDao;

        public DeleteAllAsyncTask(NotebookDao notebookDao){
            mNotebookDao = notebookDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNotebookDao.deleteAll();
            return null;
        }
    }

}
