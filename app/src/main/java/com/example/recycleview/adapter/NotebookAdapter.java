package com.example.recycleview.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.AddNotebook;
import com.example.recycleview.NotebookDetails;
import com.example.recycleview.R;
import com.example.recycleview.db.Notebook;

import java.util.ArrayList;
import java.util.List;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.ItemsHolder> {
    Context context;
    private List<Notebook> notebooks = new ArrayList<>();

    public NotebookAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notebook, parent, false);
        return new ItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        Notebook notebook = notebooks.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.priority.setCardBackgroundColor(context.getColor(notebook.getPriority()));
        }
        holder.notebookTitle.setText(notebook.getNotebookTitle());
        holder.notebookDescription.setText(notebook.getNotebookDescription1());
        holder.note.setOnClickListener(view -> {
            Intent intent = new Intent(context, NotebookDetails.class);
            intent.putExtra("priority", notebook.getPriority());
            intent.putExtra("title", notebook.getNotebookTitle());
            intent.putExtra("description", notebook.getNotebookDescription1());
            intent.putExtra("longDescription", notebook.getNotebookDescription2());
            context.startActivity(intent);
        });
        holder.update.setOnClickListener(view -> {
            Intent intent = new Intent(context, AddNotebook.class);
            intent.putExtra("ID", notebook.getNoteId());
            intent.putExtra("TITLE", notebook.getNotebookTitle());
            intent.putExtra("DESC1", notebook.getNotebookDescription1());
            intent.putExtra("DESC2", notebook.getNotebookDescription2());
            intent.putExtra("PRIORITY", notebook.getPriority());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notebooks.size();
    }

    public void initializeNotebooks(List<Notebook> mNotebooks) {
        notebooks = mNotebooks;
        notifyDataSetChanged();
    }

    public Notebook getNotebookAt(int pos) {
        return notebooks.get(pos);
    }
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout note;
        public CardView priority;
        public TextView notebookTitle;
        public TextView notebookDescription;
        public ImageView update;

        public ItemsHolder(View itemView) {
            super(itemView);
            priority = itemView.findViewById(R.id.notebookPriority);
            notebookTitle = itemView.findViewById(R.id.notebookTitle);
            notebookDescription = itemView.findViewById(R.id.notebookDetails);
            note = itemView.findViewById(R.id.linearCardView);
            update = itemView.findViewById(R.id.editNotebook);
        }
    }
}