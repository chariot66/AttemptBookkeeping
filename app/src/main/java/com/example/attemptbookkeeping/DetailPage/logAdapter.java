package com.example.attemptbookkeeping.DetailPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attemptbookkeeping.DetailActivity;
import com.example.attemptbookkeeping.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class logAdapter extends RecyclerView.Adapter<logHolder> {
    ArrayList<notelog> models;
    Context c;

    public logAdapter(Context c, ArrayList<notelog> models) {
        this.models = models;
        this.c = c;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.log_layout;
    }

    @NonNull
    @Override
    public logHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new logHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull logHolder holder, int position) {
        holder.mAmount.setText(String.valueOf(models.get(position).getAmount()));
        holder.mType.setText(models.get(position).getType());
        holder.mTime.setText(models.get(position).getTime());
        // Use when you want to put each item data to same activity

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("提示信息").setMessage("您确定要删除这条记录吗？"+
                                String.valueOf(models.get(position).getAmount()) +" "+
                        models.get(position).getType()+" "+models.get(position).getTime())
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 删除相关操作
                                notifyDataSetChanged();
                                // adapter.notifyDataSetChanged();//提示适配器更新数据
                            }
                        });
                builder.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}