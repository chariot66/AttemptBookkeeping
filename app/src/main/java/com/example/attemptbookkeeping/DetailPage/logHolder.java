package com.example.attemptbookkeeping.DetailPage;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attemptbookkeeping.R;

public class logHolder extends RecyclerView.ViewHolder {

    ConstraintLayout constraintLayout;
    TextView mAmount, mType, mTime;
    public logHolder(View itemView) {
        super(itemView);
        this.constraintLayout = itemView.findViewById(R.id.log_note);
        this.mAmount = itemView.findViewById(R.id.amount);
        this.mType = itemView.findViewById(R.id.type);
        this.mTime = itemView.findViewById(R.id.time);
    }
}

