package com.dotplays.androidroom;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class UserHolder extends RecyclerView.ViewHolder {

    public final TextView tvData;
    public UserHolder(@NonNull View itemView) {
        super(itemView);
        tvData = itemView.findViewById(R.id.tvData);
    }

}
