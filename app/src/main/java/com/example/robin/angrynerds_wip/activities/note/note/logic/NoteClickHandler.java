package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.graphics.Bitmap;
import android.view.MenuItem;
import android.view.View;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.activities.note.note.data.gui_oriented.ImageImport;

public class NoteClickHandler {

    NoteApplicationLogic mNoteApplicationLogic;

    public NoteClickHandler(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
    }

    public void onImageClicked(int pId) {
        if (pId == 0) {
            mNoteApplicationLogic.initImageImportObject();
        } else {
            Bitmap bitmap = mNoteApplicationLogic.getNoteData().getImage(pId);
            if (bitmap != null) {
                mNoteApplicationLogic.getNoteImagePopupLogic().openImagePopup(bitmap);
            } else {
                mNoteApplicationLogic.getNoteData();
            }
        }
    }

    public void onTitleClicked() {
        mNoteApplicationLogic.getNoteGui().getNoteTitle().setCursorVisible(true);
    }

    public void onTagsClicked() {
        mNoteApplicationLogic.getNoteNavigationLogic().startTagActivity();
    }

    public void onTextChanged(String pText, View pView) {
        if (pView.getId() == R.id.id_note_title) {
            mNoteApplicationLogic.getNoteData().getNote().setTitle(pText);
        } //R.id.id_event_editText_title
        else if (pView.getId() == R.id.id_note_description) {
            mNoteApplicationLogic.getNoteData().getNote().setDescription(pText);
        } //R.id.id_event_editText_title
    }

    public void onMenuItemClicked(MenuItem pItem) {
        if (pItem.getItemId() == R.id.note_action_settings) {
            mNoteApplicationLogic.getNoteData().deleteNote();
            mNoteApplicationLogic.getNoteNavigationLogic().returnToOverview();
        }
    }
}
