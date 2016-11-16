package fr.p8.m2ise.androidfacecamrecog;

import android.os.Environment;

import java.io.File;

/**
 * Created by zhaleh on 16/11/16.
 */

public final class BaseAlbumDirFactory extends AlbumStorageDirFactory {
    private static final String CAMERA_DIR = "/dcim/";

    @Override
    public File getAlbumStorageDir(String albumName) {
        return new File(
                Environment.getExternalStorageDirectory()
                        + CAMERA_DIR
                        + albumName
        );

    }
}
