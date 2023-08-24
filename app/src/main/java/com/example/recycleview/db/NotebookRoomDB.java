package com.example.recycleview.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Notebook.class , version = 3)
public abstract class NotebookRoomDB extends RoomDatabase {
    private static NotebookRoomDB instance;
    public abstract NotebookDao notebookDao();

    public static synchronized NotebookRoomDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), NotebookRoomDB.class ,
                    "notebook")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new NotebookAsyncTask(instance).execute();
        }
    };

    private static class NotebookAsyncTask extends AsyncTask<Void , Void , Void>{
        NotebookAsyncTask(NotebookRoomDB notebookRoomDB){
            NotebookDao notebookDao1 = notebookRoomDB.notebookDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
