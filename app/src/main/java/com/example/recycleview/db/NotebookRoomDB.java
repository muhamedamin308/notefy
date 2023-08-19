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
                    "notebook-data")
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
        private NotebookDao notebookDao1;

        NotebookAsyncTask(NotebookRoomDB notebookRoomDB){
            notebookDao1 = notebookRoomDB.notebookDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
//            notebookDao1.insert(
//                    new Notebook(
//                            R.color.high_priority,
//                            "Mom orders",
//                            "Your mom told you to bring somethings",
//                            "1/2 Kg Potato \n" +
//                                    "3 Kg Mango \n" +
//                                    "Breakfast"
//                    )
//            );
//            notebookDao1.insert(
//                    new Notebook(
//                            R.color.medium_priority,
//                            "Homework",
//                            "Math and Physics",
//                            "Page 192 Math \n" +
//                                    "Page 82 Math \n" +
//                                    "Page 291 Physics"
//                    )
//            );
            return null;
        }
    }
}
