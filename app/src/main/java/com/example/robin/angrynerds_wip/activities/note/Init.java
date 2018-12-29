package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;

//import com.example.robin.angrynerds_wip.activities.Data;

public class Init extends AppCompatActivity {

    //private Data mData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initData(savedInstanceState);
        initGUI();
        initApplicationLogic();
    }
/*
    private void initData (Bundle savedInstanceState) {
        //mData = new Data(savedInstanceState, this);
    }
*/
    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        mApplicationLogic = new ApplicationLogic(MockData.getNoteSample(), mGui);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();   //default action
        mApplicationLogic.onBackPressed();   // customized action
    }
}
