package com.example.robin.angrynerds_wip.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.data.models.utils.MockData;
import com.example.robin.angrynerds_wip.data.models.utils.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gui {

    private EditText mTitle;
    private EditText mText;
    private Button mStartDate;
    private Button mEndDate;
    private SeekBar mProgressBar;
    private ListView mTasks;

    public Gui(Init activity) {
        activity.setContentView(R.layout.activity_todo);

        mTitle = activity.findViewById(R.id.edit_todo_title);
        mText = activity.findViewById(R.id.edit_todo_text);
        mStartDate = activity.findViewById(R.id.edit_todo_startDate);
        mEndDate = activity.findViewById(R.id.edit_todo_endDate);
        mProgressBar = activity.findViewById(R.id.edit_todo_progressBar);
        mTasks = activity.findViewById(R.id.edit_todo_tasks);

        // TEST
        String [] aktienlisteArray = {
                "Adidas - Kurs: 73,45 €",
                "BASF - Kurs: 84,27 €",
                "Bayer - Kurs: 128,60 €",
        };
        List<String> aktienListe = new ArrayList<>(Arrays.asList(aktienlisteArray));

        //Todo todo = (Todo)MockData.tenMockData.get(0); // TODO: Muss an diese Klasse übergeben werden
        //mTasks.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<String> adapter=
                new ArrayAdapter<>(
                        activity, // Die aktuelle Activity
                        R.layout.rowlayout, // ID des Layouts für alle Listen-Elemente
                        R.id.txt_lan, // ID des CheckedTextView-Elements, wie ein einzelnes Element angezeigt werden soll
                        aktienListe); // Die Liste der Elemente
        mTasks.setAdapter(adapter);
    }


    // getter to access views
    public EditText getmTitle() {
        return mTitle;
    }

    public EditText getmText() {
        return mText;
    }

    public Button getmStartDate() {
        return mStartDate;
    }

    public Button getmEndDate() { return mEndDate; }

    public SeekBar getmProgressBar() {
        return mProgressBar;
    }

    public ListView getmTasks() { return mTasks; }


    // methods to change view attributes
    public void setmProgressBar(int progress) {
        mProgressBar.setProgress(progress);
    }
}
