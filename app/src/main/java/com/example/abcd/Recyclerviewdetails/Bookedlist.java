package com.example.abcd.Recyclerviewdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.abcd.R;
import com.example.abcd.Recyclerviewdetails.MyAdapter;
import com.example.abcd.Recyclerviewdetails.NatureModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Bookedlist extends AppCompatActivity {


    static HashSet<String> arrayList = new LinkedHashSet<>();
    static ArrayList<String> newarr ;
    static HashSet<String> idlist = new LinkedHashSet<>();
    static ArrayList<String> idarr ;
    static HashSet<String> ageinglist = new LinkedHashSet<>();
    static ArrayList<String> agearr ;
    static HashSet<String> timinglist = new LinkedHashSet<>();
    static ArrayList<String> timearr ;
    static ArrayAdapter arrayAdapter ;
    private ArrayList<String> list;
    private ArrayList<String> id;
    private ArrayList<String> age;
    private ArrayList<String> time;
    MyAdapter adapter;
    RecyclerView recyclerView;

    public Bookedlist() {
        list = new ArrayList<>(arrayList);
        id =new ArrayList<>(idlist);
        age=new ArrayList<>(ageinglist);
        time=new ArrayList<>(timinglist);

    }

    public ArrayList<String> getList() {
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedlist);

//        HashSet<String> myNameList = (HashSet<String>) getIntent().getSerializableExtra("name");
//        HashSet<String> myIdList = (HashSet<String>) getIntent().getSerializableExtra("id");
//        HashSet<String> myageList = (HashSet<String>) getIntent().getSerializableExtra("age");
//        HashSet<String> timebookedList = (HashSet<String>) getIntent().getSerializableExtra("time");

        ArrayList<String> myNameList=(ArrayList) getIntent().getSerializableExtra("name");
        ArrayList<String> myIdList=(ArrayList) getIntent().getSerializableExtra("id");
        ArrayList<String> myageList=(ArrayList) getIntent().getSerializableExtra("age");
        ArrayList<String> timebookedList=(ArrayList) getIntent().getSerializableExtra("time");



        assert myNameList != null;
        Log.i("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM",myNameList.toString());
        newarr = new ArrayList<>(myNameList);


        assert myIdList != null;
        Log.i("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",myIdList.toString());
        idarr = new ArrayList<>(myIdList);

        assert myageList != null;
        Log.i("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",myageList.toString());
        agearr = new ArrayList<>(myageList);


        assert timebookedList != null;
        Log.i("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",timebookedList.toString());
        timearr = new ArrayList<>(timebookedList);




        recyclerView =  findViewById(R.id.bookedlistrv);
        adapter = new MyAdapter(this, NatureModel.getObjectList());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//for linear configuration of layout

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}