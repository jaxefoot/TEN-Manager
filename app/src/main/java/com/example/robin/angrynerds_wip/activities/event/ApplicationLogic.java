package com.example.robin.angrynerds_wip.activities.event;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Date;

public class ApplicationLogic {

    //private Data mData;
    private Gui mGui;
    private Activity mActivity;
    private Data mData;
    private DialogFragment datePicker;
    private DialogFragment timePicker;
    private Intent mIntent;
    private Date date;

    public ApplicationLogic(Gui pGui, Activity pActivity, Data pData) {
        mGui = pGui;
        mActivity = pActivity;
        mData = pData;
        initGui();
        initListener();
    }

    private void initGui() { dataToGui();
    }

    private void initListener() {
        //Alle CliclListener, die in der GUI genutzt werden
        ClickListener clickListener;

        clickListener = new ClickListener(this);
        mGui.getmEditTextDate().setOnClickListener(clickListener);
        mGui.getmEditTextTime().setOnClickListener(clickListener);

        mGui.getmIconCloseReminder1().setOnClickListener(clickListener);
        mGui.getmIconCloseReminder2().setOnClickListener(clickListener);
        mGui.getmEditTextNewReminder().setOnClickListener(clickListener);
    }

    public void dataToGui() {
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }

    public void onBackPressed() {    }

    //to receive Date from DatePicker Fragment
    public void receiveDate(DatePicker datePicker) {
        mGui.setDate(datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear());
    }

    //receive Time from TimePicker Fragment
    public void receiveTime(int hour, int minute){
        mGui.setTime(hour + ":" + minute);
    }

    public void showDatePickerDialog(View v){
        datePicker = new DatePickerFragment();
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
    }

    public void showTimePickerDialog(View v) {
        timePicker= new TimePickerFragment();
        timePicker.show(mActivity.getFragmentManager(), "TimePicker");
    }

    public void removeReminder(int iconCloseReminder1) {
    }

    public void onNewReminderClicked() {
    }
}
