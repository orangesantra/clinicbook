package com.example.abcd.Myappointmentpackage;

import com.example.abcd.R;
import com.example.abcd.hosclidocnamelist.Hosclidoctor;
import com.example.abcd.hosclidocnamelist.NatModel;

import java.util.ArrayList;
import java.util.List;

public class NatureModelMyappointment {


    private int imageID;
    private String title,id;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID11111) {
        this.imageID = imageID11111;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId(){return id;}

    public void setId(String id){
        this.id=id;
    }

    public static List<NatureModelMyappointment> getObjectList() {

        List<NatureModelMyappointment> dataList = new ArrayList<>();

        ArrayList myAppointmentnamelist = getMyAppointmentlist();
        ArrayList objectidlist = getobjectIdlist();

        for (int i = 0; i < myAppointmentnamelist.size(); i++) {
            NatureModelMyappointment nature = new NatureModelMyappointment();
            nature.setTitle(myAppointmentnamelist.get(i).toString());
            nature.setId(objectidlist.get(i).toString());
            dataList.add(nature);
        }

        return dataList;
    }

    private static ArrayList getMyAppointmentlist() {


        ArrayList list1 = new ArrayList<>();
        if(Myappointmentlist.appointmentarraylist!=null) {

            list1.addAll(Myappointmentlist.appointmentarraylist);
        }

        return list1;
    }

    private static ArrayList getobjectIdlist() {


        ArrayList list2 = new ArrayList<>();
        if(Myappointmentlist.objectIdlist!=null) {

            list2.addAll(Myappointmentlist.objectIdlist);
        }

        return list2;
    }

}
