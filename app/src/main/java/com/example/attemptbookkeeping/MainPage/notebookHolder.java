package com.example.attemptbookkeeping.MainPage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.attemptbookkeeping.R;

public class notebookHolder extends RecyclerView.ViewHolder {
    public RelativeLayout notebookRelativeLayout;
    public TextView noteName;
    public TextView noteDescription;
    public ImageView noteimg;

    public notebookHolder(View itemView){
        super(itemView);

        this.noteimg = itemView.findViewById(R.id.noteImage);
        this.noteDescription = itemView.findViewById(R.id.noteDescription);
        this.noteName = itemView.findViewById(R.id.noteName);
        this.notebookRelativeLayout = itemView.findViewById(R.id.noteRelative);

    }
}
