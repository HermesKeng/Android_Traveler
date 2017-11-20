package com.example.keng.traveler;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by keng on 18/11/2017.
 */

public class Location {

    private String location;
    private String arriveDate;
    private String leaveDate;
    private int imageID;

    public Location(String location,int res,String arriveDate,String leaveDate){
        this.location = location.toUpperCase();
        this.arriveDate = arriveDate;
        this.leaveDate = leaveDate;
        this.imageID = res;
    }
    public String getLocation(){
        return location;
    }
    public int getImageID(){return imageID;}

}
