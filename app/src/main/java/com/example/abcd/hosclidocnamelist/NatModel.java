package com.example.abcd.hosclidocnamelist;

import android.util.Log;

import com.example.abcd.Recyclerviewdetails.Requestlist;
import com.example.abcd.hosclidocnamelist.Hosclidoctor;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class NatModel {

    private int imageID;
    private String title;
    private String tiid;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID11111) {
        this.imageID = imageID11111;
    }

    public String getTitle() {
        return title;

    }
    public String getTiid() {
        return tiid;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTiid(String title) {
        this.tiid = title;
    }

    public static List<NatModel> getObjectList1() {

        List<NatModel> dataList = new ArrayList<>();

        ArrayList cliniclist = getclinicnames();
        ArrayList clinicIdlist = getClinicIds();

        for (int i = 0; i < cliniclist.size(); i++) {
            NatModel nature = new NatModel();
            nature.setTitle(cliniclist.get(i).toString());
            nature.setTiid(clinicIdlist.get(i).toString());
            dataList.add(nature);
        }

        return dataList;


    }


    private static ArrayList getclinicnames() {


        ArrayList clinic = new ArrayList<>();
        if(Hosclidoctor.k!=null) {

            clinic.addAll(Hosclidoctor.k);
        }

        return clinic;
    }

    private static ArrayList getClinicIds(){
        ArrayList clinicId = new ArrayList<>();

        if(Hosclidoctor.l!=null) {
            clinicId.addAll(Hosclidoctor.l);
        }
        return clinicId;

    }
}
