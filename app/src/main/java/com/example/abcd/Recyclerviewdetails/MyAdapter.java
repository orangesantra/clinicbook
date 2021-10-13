package com.example.abcd.Recyclerviewdetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abcd.EveryoneDetail.Individualdetails;
import com.example.abcd.R;

import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NatureModel> objectList;
    private LayoutInflater inflater;
    Intent intent;


    public MyAdapter(Context context, List<NatureModel> objectList) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NatureModel current = objectList.get(position);
        holder.setData(current, position);

        holder.setListeners();//when any image is clicked perform something
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        private int position;
        private NatureModel currentObject;
        private TextView title,idtitle,age,time;
        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       = (TextView)  itemView.findViewById(R.id.tvtitle);
            idtitle       = (TextView)  itemView.findViewById(R.id.tvid);
            age =itemView.findViewById(R.id.tvAge);
            time=itemView.findViewById(R.id.tvtime);
            linearLayout = itemView.findViewById(R.id.lin);
        }

        public void setData(NatureModel currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.idtitle.setText(currentObject.getTiid());
            this.age.setText(currentObject.getTiAge());
            this.time.setText(currentObject.getTiAllotedTime());
            this.position = position;
            this.currentObject = currentObject;

        }

        public void setListeners() {//method for clicking images
            title.setOnClickListener(MyViewHolder.this);
            idtitle.setOnClickListener(MyViewHolder.this);
            age.setOnClickListener(MyViewHolder.this);
            time.setOnClickListener(MyViewHolder.this);
            linearLayout.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.lin) {
                final Intent intent;
                intent = new Intent(getApplicationContext(), Individualdetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("itemname", title.getText().toString());
                intent.putExtra("itemid", idtitle.getText().toString());
                intent.putExtra("age",age.getText().toString());
                intent.putExtra("time",time.getText().toString());
                getApplicationContext().startActivity(intent);
            }
        }



    }

}
