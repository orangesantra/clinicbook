package com.example.abcd.Recyclerviewdetails;

import java.util.ArrayList;
import java.util.List;

public class NatureModel {

    private int imageID;
    private String title;
    private String tiid;
    private String tiage;
    private String titime;

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
    public String getTiAge() {
        return tiage;

    }
    public String getTiAllotedTime() {
        return titime;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTiid(String id) {
        this.tiid = id;
    }

    public void setTiAge(String age) {
        this.tiage = age;
    }

    public void setTiAllotedTime(String time) {
        this.titime = time;
    }

    public static List<NatureModel> getObjectList() {

        List<NatureModel> dataList = new ArrayList<>();

        ArrayList namelist = getNames();
        ArrayList idlist = getObjectId();
        ArrayList AgeList = getAge();
        ArrayList TimeList = getTimeAllloted();


        for (int i = 0; i < idlist.size(); i++) {
            NatureModel nature = new NatureModel();
            nature.setTitle(namelist.get(i).toString());
            nature.setTiid(idlist.get(i).toString());
            nature.setTiAge(AgeList.get(i).toString());
            nature.setTiAllotedTime(TimeList.get(i).toString());
            dataList.add(nature);
        }

        return dataList;
    }


    private static ArrayList getNames() {


        ArrayList names = new ArrayList<>();
        if(Bookedlist.newarr!=null) {
            names.addAll(Bookedlist.newarr);
        }
        return names;
    }

    private static ArrayList getObjectId(){
        ArrayList objectIds = new ArrayList<>();

        if(Bookedlist.idarr!=null) {
            objectIds.addAll(Bookedlist.idarr);
        }
        return objectIds;

    }

    private static ArrayList getAge(){
        ArrayList agelist = new ArrayList<>();

        if(Bookedlist.agearr!=null) {
            agelist.addAll(Bookedlist.agearr);
        }
        return agelist;

    }

    private static ArrayList getTimeAllloted(){
        ArrayList timelist = new ArrayList<>();

        if(Bookedlist.timearr!=null) {
            timelist.addAll(Bookedlist.timearr);
        }
        return timelist;

    }
}
