package com.example.robin.angrynerds_wip.activities.note.note.logic;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.robin.angrynerds_wip.activities.note.note.gui.ImageOverlay;
import com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher.ImageOverlayListener;

public class NoteImagePopupLogic {
    NoteApplicationLogic mNoteApplicationLogic;
    ImageOverlay mImageOverlay;
    ImageOverlayListener mImageOverlayListener;

    public NoteImagePopupLogic(NoteApplicationLogic pNoteApplicationLogic) {
        this.mNoteApplicationLogic = pNoteApplicationLogic;
        this.mImageOverlayListener = new ImageOverlayListener(pNoteApplicationLogic);
    }



    public void openImagePopup(Bitmap pBitmap) {
        if (mNoteApplicationLogic.getNoteData().getActivity().isActive()) {
            View displayMetrics = mNoteApplicationLogic.getNoteData().getActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
            mImageOverlay = new ImageOverlay(pBitmap, displayMetrics.getWidth(), displayMetrics.getHeight(), mImageOverlayListener);
            mImageOverlay.display(mNoteApplicationLogic.getNoteData().getActivity());

        }
    }
    
    public void changeConfiguration(Configuration pConfiguration){
        float dpRatio = this.mNoteApplicationLogic
                .getNoteData().
                        getActivity()
                .getApplicationContext()
                .getResources()
                .getDisplayMetrics()
                .density;
        int displayHeight = (int)(pConfiguration.screenHeightDp*dpRatio)+1;
        int displayWidth = (int)(pConfiguration.screenWidthDp*dpRatio)+1;

        if (mImageOverlay != null && mImageOverlay.isDisplayed()) {
            mImageOverlay.changeOrientation(mNoteApplicationLogic.getNoteData().getActivity() ,displayWidth, displayHeight);
        }
    }

    public void closePopup() {
        this.mImageOverlay = null;
    }
}
