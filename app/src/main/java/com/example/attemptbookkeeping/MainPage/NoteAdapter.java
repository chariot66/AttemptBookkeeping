package com.example.attemptbookkeeping.MainPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attemptbookkeeping.Database.DBTableHelper;
import com.example.attemptbookkeeping.DetailActivity;
import com.example.attemptbookkeeping.R;
import com.example.attemptbookkeeping.notebook;
import com.example.attemptbookkeeping.tools.DataHolder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<notebookHolder> {
    Context c;
    ArrayList<notebook> models;
    DBTableHelper dbnote;
    // Constructor of the MyAdapter Class
    public NoteAdapter(Context c, ArrayList<notebook> models, DBTableHelper DB) {
        this.c = c;
        this.models = models;
        this.dbnote = DB;
    }
    public void setNewData(ArrayList<notebook> models_n) {
        this.models.clear();
        this.models.addAll(models_n);
    }
    @NonNull
    @Override
    public notebookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
// convert xml to view obj
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notebook_item_layout, null);
        return new notebookHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull notebookHolder holder, int position) {
//bind data to our views
        holder.noteName.setText(models.get(position).getName());
        holder.noteDescription.setText(models.get(position).getDescription());
        holder.noteimg.setImageResource(models.get(position).getImg());
// Animation
        holder.notebookRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from item click
                String noteName = models.get(position).getName();
                String noteDes = models.get(position).getDescription();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)
                        holder.noteimg.getDrawable();
                // get bitmap from drawable
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // compress image
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                //convert to bytes array
                byte[] bytes = stream.toByteArray();
                // intent, put data to intent, start activity
                DataHolder.getInstance().setItem(noteName);
                Intent intent = new Intent(c, DetailActivity.class);
                intent.putExtra("nName", noteName);
                intent.putExtra("nDes", noteDes);
                intent.putExtra("nimg", bytes);
                c.startActivity(intent);
            }
        });

        // 尝试长按删除 失败
//        holder.notebookRelativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                // get data from item click
//                String noteName = models.get(position).getName();
//                dbnote.deleteData(noteName);
//                return true;
//            }
//        });
        Animation animation = AnimationUtils.loadAnimation(c,
                android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
}
