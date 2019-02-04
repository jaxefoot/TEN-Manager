package com.example.robin.angrynerds_wip.activities.note.note.logic.listener_watcher;

import android.content.DialogInterface;

import com.example.robin.angrynerds_wip.activities.note.note.logic.NoteCameraImportLogic;

public class DialogClickListener implements DialogInterface.OnClickListener{

    private NoteCameraImportLogic mImageImport;

    public DialogClickListener(NoteCameraImportLogic pImageImport) {
        this.mImageImport = pImageImport;
    }

    @Override
    public void onClick(DialogInterface dialog, int option) {

        switch ( option ) {
            case 0:
                mImageImport.importImageFromGallery();
                break;
            case 1:
                mImageImport.importImageFromCamera();
                break;
            default:
                break;
        }
    }
}
