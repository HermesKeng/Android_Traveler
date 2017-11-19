package com.example.keng.traveler;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by keng on 18/11/2017.
 */

public class Location {

    private String location;
    private int imageId;
    private String arriveDate;
    private String leaveDate;

    public Location(String location,int id,String arriveDate,String leaveDate){
        this.location = location.toUpperCase();
        this.imageId = id;
        this.arriveDate = arriveDate;
        this.leaveDate = leaveDate;
    }
    public String getLocation(){
        return location;
    }
    public int getImageId(){
        return imageId;
    }
}
