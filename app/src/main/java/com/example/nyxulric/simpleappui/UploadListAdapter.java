package com.example.nyxulric.simpleappui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.ViewHolder>{

    public List<String> filenameList;
    public List<String> fileDoneList;

    public UploadListAdapter(List<String> filenameList, List<String> fileDoneList){
        this.fileDoneList = fileDoneList;
        this.filenameList = filenameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String filename = filenameList.get(position);
        holder.fileNameView.setText(filename);

        String fileDone = fileDoneList.get(position);

        if (fileDone.equals("Uploading")){
            holder.fileDoneView.setImageResource(R.drawable.ic_loading);
        }else {
            holder.fileDoneView.setImageResource(R.drawable.ic_check_circle_black_24dp);
        }

    }

    @Override
    public int getItemCount() {
        return filenameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TextView fileNameView;
        public ImageView fileDoneView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            fileNameView = mView.findViewById(R.id.upload_filename);
            fileDoneView = mView.findViewById(R.id.upload_loading);
        }
    }
}
