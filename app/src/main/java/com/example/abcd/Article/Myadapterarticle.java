package com.example.abcd.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcd.R;
import com.example.abcd.Recyclerviewdetails.MyAdapter;

import java.util.List;

public class Myadapterarticle extends RecyclerView.Adapter<Myadapterarticle.MyViewHolder> {

    private List<Naturemodelarticle> objectList;
    private LayoutInflater inflater;

    public Myadapterarticle(Context context, List<Naturemodelarticle> objectList) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_itemarticle, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }



    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Naturemodelarticle current = objectList.get(position);
        holder.setData(current, position);
        holder.setListeners();//when any image is clicked perform something
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView imgThumb, imgDelete, imgCopy;
        private int position;
        private Naturemodelarticle currentObject;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       = (TextView)  itemView.findViewById(R.id.tvTitle);
            imgThumb    = (ImageView) itemView.findViewById(R.id.img_thumb);
            imgDelete   = (ImageView) itemView.findViewById(R.id.img_delete);
            imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);
        }

        public void setData(Naturemodelarticle currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.imgThumb.setImageResource(currentObject.getImageID());
            this.position = position;
            this.currentObject = currentObject;
        }

        public void setListeners() {//method for clicking images
            imgDelete.setOnClickListener(MyViewHolder.this);
            imgCopy.setOnClickListener(MyViewHolder.this);
            imgThumb.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }


}
