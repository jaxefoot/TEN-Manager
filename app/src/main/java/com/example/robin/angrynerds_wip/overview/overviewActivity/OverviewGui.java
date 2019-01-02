package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.widget.LinearLayout;

import com.example.robin.angrynerds_wip.R;

public class OverviewGui {

    private OverviewInit mActivity;

    //private LinearLayout mContainer1;
    //private LinearLayout mContainer2;
    private int mContainer1Id;
    private int mContainer2Id;

    OverviewGui(OverviewInit pActivity){
        mActivity = pActivity;
        mActivity.setContentView(R.layout.activity_overview);

        //mContainer1 = mActivity.findViewById(R.id.id_overview_linearLayout_container1);
        //mContainer2 = mActivity.findViewById(R.id.id_overview_linearLayout_container2);

        mContainer1Id = R.id.id_overview_linearLayout_container1;
        mContainer2Id = R.id.id_overview_linearLayout_container2;
    }

    public int getContainer1Id(){
        return mContainer1Id;
    }

    public int getContainer2Id(){
        return mContainer2Id;
    }
}
