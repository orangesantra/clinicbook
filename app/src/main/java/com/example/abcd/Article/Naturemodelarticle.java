package com.example.abcd.Article;

import com.example.abcd.R;

import java.util.ArrayList;
import java.util.List;

public class Naturemodelarticle {


    private int imageID;
    private String title;

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

    public static List<Naturemodelarticle> getObjectList() {

        List<Naturemodelarticle> dataList = new ArrayList<>();
//        int[] images = getImages();

//        for (int i = 0; i < images.length; i++) {
//            Naturemodelarticle nature = new Naturemodelarticle();
//            nature.setImageID(images[i]);
//            nature.setTitle("Picture " + i);
//            dataList.add(nature);
//        }
        return dataList;
    }

//    private static int[] getImages() {
//
//
//
//        return images;
//    }
}
