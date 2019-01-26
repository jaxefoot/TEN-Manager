package com.example.robin.angrynerds_wip.overview.superClasses;

import android.os.Bundle;

import com.example.robin.angrynerds_wip.data.models.utils.BundleKeys;

public abstract class OverviewFragmentData {
    String mID;
    String mTitle;
    int mColor;

    public void addData(Bundle pData){
        mID = pData.getString(BundleKeys.keyTENID);
        mTitle = pData.getString(BundleKeys.keyTENTitle);
        mColor = pData.getInt(BundleKeys.keyTENColor);
    }

    public String getID(){
        return mID;
    }

    public String getTitle(){
        return mTitle;
    }

    public int getColor(){
        return mColor;
    }
}