package com.example.keng.traveler;

import java.util.Stack;

/**
 * Created by keng on 20/11/2017.
 */

public class Schedule {
    private String name;
    private String district;
    private String description;
    private int imageID;
    private int[] imageIDList={R.mipmap.food,R.mipmap.scenery
    };

    public Schedule (String name,String district,String description, int type){
        this.name=name;
        this.district=district;
        this.description = description;
        this.imageID=imageIDList[type];
    }

    public String getName(){
        return name;
    }
    public String getDistrict(){
        return district;
    }
    public String getDescription(){
        return description;
    }
    public int getImageID(){
        return  imageID;
    }
    public void setName(String newName){
        this.name=newName;
    }
    public void setDistrict(String newDistrict){
        this.district = newDistrict;
    }
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
}
