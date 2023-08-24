package com.example.recycleview.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notebook {
    public String notebookTitle;
    public String notebookDescription1;
    public String notebookDescription2;
    public int priority;
    @PrimaryKey(autoGenerate = true)
    int noteId;

    public Notebook(int priority,
                    String notebookTitle,
                    String notebookDescription1,
                    String notebookDescription2) {
        this.priority = priority;
        this.notebookTitle = notebookTitle;
        this.notebookDescription1 = notebookDescription1;
        this.notebookDescription2 = notebookDescription2;
    }

    public Notebook() {
    }

    public String getNotebookDescription2() {
        return notebookDescription2;
    }

    public int getPriority() {
        return priority;
    }

    public String getNotebookTitle() {
        return notebookTitle;
    }

    public String getNotebookDescription1() {
        return notebookDescription1;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
}
