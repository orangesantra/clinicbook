package com.example.abcd.hosclidocnamelist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abcd.R;
import com.example.abcd.hosclidocdetailslist.Clinicdetails;
import com.example.abcd.hosclidocdetailslist.DoctorsDetails;
import com.example.abcd.hosclidocdetailslist.HospitalDetails;

import java.util.List;

import static com.parse.Parse.getApplicationContext;

public class MAdapter extends RecyclerView.Adapter<com.example.abcd.hosclidocnamelist.MAdapter.MyViewHolder2> {

    private List<NatModel> objectList;
    private LayoutInflater inflater;



    public MAdapter(Context context, List<NatModel> objectList) {
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }


    @Override
    public MAdapter.MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.clinichosdoclist_item, parent, false);
        MAdapter.MyViewHolder2 holder = new MAdapter.MyViewHolder2(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(com.example.abcd.hosclidocnamelist.MAdapter.MyViewHolder2 holder, int position) {
        NatModel current = objectList.get(position);
        holder.setData(current, position);

        holder.setListeners();
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView imgThumb;
        private int position;
        private NatModel currentObject;
        private TextView title,idtitle;
        private LinearLayout linearLayout;

        public MyViewHolder2(View itemView) {
            super(itemView);
            title       = (TextView)  itemView.findViewById(R.id.tvTitle1);
            idtitle       = (TextView)  itemView.findViewById(R.id.tvid1);
            imgThumb    = (ImageView) itemView.findViewById(R.id.imageView12);
            linearLayout = itemView.findViewById(R.id.lin2);
        }

        public void setData(NatModel currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.idtitle.setText(currentObject.getTiid());
            this.imgThumb.setImageResource(currentObject.getImageID());
            this.position = position;
            this.currentObject = currentObject;

        }

        public void setListeners() {

            imgThumb.setOnClickListener(com.example.abcd.hosclidocnamelist.MAdapter.MyViewHolder2.this);
            title.setOnClickListener(com.example.abcd.hosclidocnamelist.MAdapter.MyViewHolder2.this);
            linearLayout.setOnClickListener(com.example.abcd.hosclidocnamelist.MAdapter.MyViewHolder2.this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.lin2) {
                final Intent intent1;
                final Intent intent2;
                final Intent intent3;

               if( Hosclidoctor.faculty.equals("ClinicData")) {
                   intent1 = new Intent(getApplicationContext(), Clinicdetails.class);

                   intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   intent1.putExtra("itemname", title.getText().toString());
                   intent1.putExtra("itemid", idtitle.getText().toString());
                   intent1.putExtra("identity", Hosclidoctor.s1);
                   getApplicationContext().startActivity(intent1);
               }

                if( Hosclidoctor.faculty.equals("DoctorData")) {
                    intent2 = new Intent(getApplicationContext(), DoctorsDetails.class);

                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtra("itemname", title.getText().toString());
                    intent2.putExtra("itemid", idtitle.getText().toString());
                    intent2.putExtra("identity", Hosclidoctor.s1);
                    getApplicationContext().startActivity(intent2);
                }

                if( Hosclidoctor.faculty.equals("HospitalData")) {
                    intent3 = new Intent(getApplicationContext(), HospitalDetails.class);

                    intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent3.putExtra("itemname", title.getText().toString());
                    intent3.putExtra("itemid", idtitle.getText().toString());
                    intent3.putExtra("identity", Hosclidoctor.s1);
                    getApplicationContext().startActivity(intent3);
                }


            }
        }



    }

}
