package com.example.robin.angrynerds_wip.activities.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.data.models.utils.MockData;

//import com.example.robin.angrynerds_wip.activities.Data;

public class Init extends AppCompatActivity {

    private Data mData;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;
    private EventDispersion mEventDispersion;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initGUI();
        initApplicationLogic();
        initEventDispersion();
    }

    private void initData(){
        mData = new Data(this);
    }

    private void initGUI () {
        mGui = new Gui(this);
    }

    private void initApplicationLogic () {
        //TODO Leere Notiz bzw. übergebene Notiz einfügen
        mApplicationLogic = new ApplicationLogic(MockData.getNoteSample(), mGui, mData);
    }

    private void initEventDispersion() {
        mEventDispersion = new EventDispersion(mApplicationLogic);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        //Data.saveDataInBundle(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mApplicationLogic.onActivityReturned(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        mEventDispersion.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // return super.onContextItemSelected(item);
        return mEventDispersion.onContextItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mApplicationLogic.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();   //default action
        //mApplicationLogic.onBackPressed();   // customized action
    }
}
