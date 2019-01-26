package com.example.robin.angrynerds_wip.overview.overviewActivity;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;
import com.example.robin.angrynerds_wip.overview.eventFragment.OverviewEventInit;
import com.example.robin.angrynerds_wip.overview.header.createFragment.OverviewHeaderCreateFragment;
import com.example.robin.angrynerds_wip.overview.header.deleteFragment.OverviewHeaderDeleteFragment;
import com.example.robin.angrynerds_wip.overview.imageFragment.OverviewImageInit;
import com.example.robin.angrynerds_wip.overview.noteFragment.OverviewNoteInit;
import com.example.robin.angrynerds_wip.overview.todoFragment.OverviewTodoInit;

import java.util.ArrayList;

public class OverviewFragmentFactory {

    public Fragment createHeaderCreateFragment(){
        return new OverviewHeaderCreateFragment();
    }

    public Fragment createHeaderDeleteFragment() { return new OverviewHeaderDeleteFragment(); }

    // Generates Fragments from a List of TENs
    public ArrayList<Fragment> createTENFragments(ArrayList<TEN> pTENs){
        ArrayList<Fragment> fragments = new ArrayList();
        for(TEN ten : pTENs) {
            if(ten.getClass() == Todo.class){
                OverviewTodoInit fragment = new OverviewTodoInit();
                fragment.setArguments(ten.getBundle());
                fragments.add(fragment);
            }
            if(ten.getClass() == Event.class){
                OverviewEventInit fragment = new OverviewEventInit();
                fragment.setArguments(ten.getBundle());
                fragments.add(fragment);
            }
            if(ten.getClass() == Note.class) {
                Log.d("LOGTAG",((Note)ten).getPictures().size() + "");
                if(((Note)ten).getPictures().isEmpty()) {
                    OverviewNoteInit fragment = new OverviewNoteInit();
                    fragment.setArguments(ten.getBundle());
                    fragments.add(fragment);
                } else {
                    OverviewImageInit fragment = new OverviewImageInit();
                    fragment.setArguments(ten.getBundle());
                    fragments.add(fragment);
                }
            }
        }
        return fragments;
    }
}
