package com.example.robin.angrynerds_wip.activities.todo;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import de.fhdw.bfwi316b.set.colorchooser.activities.ActivityUtilities;
//import de.fhdw.bfwi316b.set.colorchooser.activities.EventData;

//Authors: Sertan Soner Cetin, Florian Rath
public class TodoApplicationLogic {

    //private EventData mData;

    private Gui mGui;
    private Data mData;

    private ClickListener mClickListener;
    private CheckedChangeListener mCheckedChangeListener;

    private ArrayList<Task> mTasks;
    private DialogFragment datePicker;
    private AppCompatActivity mActivity;
    private TasksAdapter mTaskAdapter;

    private View mActiveDatePickerButton; // der Button, mit dem der DatePicker geöffnet wurde


    //Hier muss noch EventData rein
    public TodoApplicationLogic(Gui gui, AppCompatActivity pActivity, Data pData) {
        mActivity = pActivity;
        mGui = gui;
        mData = pData;
        mTasks = mData.getmTodo().getTasks();
        createList();
        initGui();
        initListener();
    }



    //Author: Florian Rath
    private void initGui() {
        //initialize Toolbar including menu and back button
        mActivity.setSupportActionBar(mGui.getmToolbar());
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        dataToGui();
        createList();
    }

    //Author: Florian Rath
    private void initListener() {
        mClickListener = new ClickListener(this);
        mCheckedChangeListener = new CheckedChangeListener(this);

        MenuItemClickListener menuItemClickListener;
        menuItemClickListener = new MenuItemClickListener(this);

        mGui.getmToolbar().setNavigationOnClickListener(mClickListener);
        mGui.getmToolbar().setOnMenuItemClickListener(menuItemClickListener);
        mGui.getmStartDate().setOnClickListener(mClickListener);
        mGui.getmEndDate().setOnClickListener(mClickListener);
        mGui.getmButton().setOnClickListener(mClickListener);

        mGui.getmTitle().addTextChangedListener(new TextWatcher(this,mGui.getmTitle()));
        mGui.getmText().addTextChangedListener(new TextWatcher(this,mGui.getmText()));

        //mGui.getmCheckBox().setOnClickListener(clickListener);
        //mGui.getmRowLayout().setOnClickListener(clickListener);
    }

    //Author: Florian Rath
    public void dataToGui() {
        mGui.setFocusableInTouchmode(!mData.getmIsNew());

        Date date = Calendar.getInstance().getTime();

        Todo todo = mData.getmTodo();
        mGui.setmTitle(todo.getTitle());
        mGui.setmText(todo.getNote());
        mGui.setColor(todo.getColor(), todo.getAccentColor());
        mGui.setDate(formatDate(todo.getStartDate()), mGui.getmStartDate());
        mGui.setDate(formatDate(todo.getEndDate()), mGui.getmEndDate());
    }

    //Author: Florian Rath
    //to receive Date from DatePicker Fragment
    public void receiveDate(Date date) {
        //mGui.setDate(datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear(), mActiveDatePickerButton);
        if(mActiveDatePickerButton.getId() == R.id.edit_todo_startDate){
            try{
                if(date.after(new SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN).parse(mGui.getmEndDate().getText().toString()))){
                    mGui.displayToast(mActivity, "Das Startdatum kann nicht hinter dem Enddatum liegen");
                }else{
                    mGui.setDate(formatDate(date), mActiveDatePickerButton);
                }
            }catch(java.text.ParseException e){
                Log.e("ParseError", e.getMessage());
            }
        }
        else if(mActiveDatePickerButton.getId() == R.id.edit_todo_endDate){
            try{
                if(date.before(new SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN).parse(mGui.getmStartDate().getText().toString()))){
                    mGui.displayToast(mActivity, "Das Enddatum kann nicht vor dem Startdatum liegen");

                }else{
                    mGui.setDate(formatDate(date), mActiveDatePickerButton);
                }
            }catch(java.text.ParseException e){
                Log.e("ParseError", e.getMessage());
            }
        }

        UpdateTodo();
    }
    //Author: Florian Rath
    public String formatDate(Date date){
        DateFormat displayFormat = new SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN);
        return displayFormat.format(date);
    }
    //Author: Florian Rath
    public void showDatePickerDialog(View v){
        datePicker = new DatePickerFragment();
        if(v.getId() == R.id.edit_todo_startDate){((DatePickerFragment) datePicker).setDate(mData.getmTodo().getStartDate());}
        else if(v.getId() == R.id.edit_todo_endDate){((DatePickerFragment)datePicker).setDate(mData.getmTodo().getEndDate());}
        datePicker.show(mActivity.getFragmentManager(), "DatePicker");
        mActiveDatePickerButton = v;
    }

    //Author: Florian Rath
    //Return to overview if back pressed / Event deleted / toolbar navigation
    public void returnToOverview() {
        //mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
        for(int i = 0; i < mTasks.size();i++) {
            if (mTasks.get(i).getDescription() == null || mTasks.get(i).getDescription().equals(""))
                mTasks.remove(i--);
        }

        try {
            UpdateTodo();

            Todo todo = mData.getmTodo();
            if (todo.getTitle().isEmpty() && todo.getTasks().size() <= 1 && todo.getNote().isEmpty())
            {
                mData.deleteTodo();
            }
        }
        catch(Exception e){

        }

        mActivity.finish();
    }

    //Author: Florian Rath
    //Toolbar menu is clicked
    public void onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.todo_action_delete) {
            mData.deleteTodo();
            returnToOverview();
        }
        else if(item.getItemId() == R.id.todo_action_share){
            mData.shareTodo(formatDate(mData.getmTodo().getStartDate()), formatDate(mData.getmTodo().getEndDate()));
        }
    }
    //Author: Sertan Soner Cetin
    //Hier wird die Liste erzeugt
    public void createList(){
        mGui.setmChoiceMode();
        mTaskAdapter =
                new TasksAdapter(
                        mActivity, //Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für ale Listen-Elemente
                        mTasks,
                        this); // Die Liste der Elemente
        mGui.setmAdapter(mTaskAdapter);

        updateProgress();
    }

    //Author: Sertan Soner Cetin
    public void updateProgress(){

        mData.getmTodo().setTasks(mTasks);

        mGui.setmProgressText((int) (mData.getmTodo().getProgress() * 100) + " %");

        //Hier lag der Fehler bei dem Wechsel der Ansichten, wollen wir uns morgen angucken
        //UpdateTodo();
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {
    }
    //Author: Sertan Soner Cetin
    //Remove String from TagList and notify adapter
    void onDeleteButtonClicked(int id){
        mTasks.remove(id);
        mTaskAdapter.notifyDataSetChanged();
        updateProgress();
    }

    //Insert user input into TagList
    void onTextChanged(String s, View mView) {

        if (mView.getId() == R.id.edit_todo_title) {
            mData.setTitle(s);
        } //R.id.id_event_editText_title
        else if (mView.getId() == R.id.edit_todo_text) {
            mData.setText(s);
        } //R.id.id_event_editText_title
        else {
            mTasks.get(mView.getId()).setDescription(s);
            updateProgress();
        }
    }

    //Author: Sertan Soner Cetin
    private void addInputTaskField() {
        mTasks.add(new Task());
        mTaskAdapter.notifyDataSetChanged();
        updateProgress();
    }

    //Author: Sertan Soner Cetin
    public ArrayList<Task> getmTasks()
    {
        return mTasks;
    }

    //Author: Sertan Soner Cetin
    //Return number of Strings in TagList
    int getTasksItemCount(){
        return mGui.getTasksItemCount();
    }

    public ClickListener getClickListener() {
        return mClickListener;
    }
    public CheckedChangeListener getmCheckedChangeListener() { return mCheckedChangeListener;}

    //Author: Sertan Soner Cetin
    public void UpdateTodo()
    {
        Todo todo = mData.getmTodo();
        todo.setTitle(mGui.getmTitle().getText().toString());
        todo.setNote(mGui.getmText().getText().toString());
        try{
            todo.setStartDate((Date) new SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN).parse(mGui.getmStartDate().getText().toString()));
            todo.setEndDate((Date) new SimpleDateFormat("EEEE, dd. MMMM yyyy", Locale.GERMAN).parse(mGui.getmEndDate().getText().toString()));
        }
        catch (ParseException e){
            Log.e("florian","Fehler" + mGui.getmStartDate().getText().toString());
        }
        mData.updateTodo();
    }

    //Author: Sertan Soner Cetin
    public void onConfigurationChanged(Gui pGui) {
        mGui = pGui;
        mTasks = mData.getmTodo().getTasks();
        createList();
        initGui();
        initListener();
    }

    public void addButtonClicked() {
        addInputTaskField();
    }
}