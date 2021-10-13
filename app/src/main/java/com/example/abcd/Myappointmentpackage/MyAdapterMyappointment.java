package com.example.abcd.Myappointmentpackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abcd.Confirmationscreen;
import com.example.abcd.R;


import java.util.List;

import static com.parse.Parse.getApplicationContext;

//import static com.parse.Parse.getApplicationContext;

public class MyAdapterMyappointment extends RecyclerView.Adapter<MyAdapterMyappointment.MyViewHolder> {


    private List<NatureModelMyappointment> objectList;
    private LayoutInflater inflater;

    public MyAdapterMyappointment(Context context, List<NatureModelMyappointment> objectList) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_itemmyappointment, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NatureModelMyappointment current = objectList.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView id;

        private int position;
        private NatureModelMyappointment currentObject;
        private LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            title       = (TextView)  itemView.findViewById(R.id.myappointmentitemname);
            id          =(TextView) itemView.findViewById(R.id.objectid);
            linearLayout = itemView.findViewById(R.id.linx);

        }

        public void setData(NatureModelMyappointment currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.id.setText(currentObject.getId());
            this.position = position;
            this.currentObject = currentObject;
        }

        public void setListeners() {
            title.setOnClickListener(com.example.abcd.Myappointmentpackage.MyAdapterMyappointment.MyViewHolder.this);
            linearLayout.setOnClickListener(com.example.abcd.Myappointmentpackage.MyAdapterMyappointment.MyViewHolder.this);;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myappointmentitemname:

                case R.id.addressitemname:

                case R.id.linx:

                    final Intent intent;
                    intent = new Intent(getApplicationContext(), Confirmationscreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ClinicName", title.getText().toString());
                    intent.putExtra("ClinicId",id.getText().toString());
                    intent.putExtra("UniqueUserId",Myappointmentlist.identity);

                    getApplicationContext().startActivity(intent);

                    break;

            }
        }
    }

    }
