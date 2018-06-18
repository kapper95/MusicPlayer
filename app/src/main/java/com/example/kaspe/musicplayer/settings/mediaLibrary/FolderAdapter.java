package com.example.kaspe.musicplayer.settings.mediaLibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kaspe.musicplayer.R;

import static android.content.ContentValues.TAG;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private String[] folders;
    private OnItemClickListener onItemClickListener;


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.textview_simple_textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener != null){
                onItemClickListener.onClick(getAdapterPosition());
            }
        }
    }

    public FolderAdapter(String[] folders){
        this.folders = folders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_simple_textview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String folder = folders[position];
        holder.textView.setText(folder);
    }

    @Override
    public int getItemCount() {
        return folders.length;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

}
