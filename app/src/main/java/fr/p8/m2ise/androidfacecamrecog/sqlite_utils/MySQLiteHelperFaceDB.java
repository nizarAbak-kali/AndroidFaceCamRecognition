package fr.p8.m2ise.androidfacecamrecog.sqlite_utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nizar on 04/10/16.
 */

public class MySQLiteHelperFaceDB extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FaceDB";
    // Books table name
    private static final String TABLE_BOOKS = "books";


    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_IMAGE};



    public MySQLiteHelperFaceDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_FACE_TABLE = "CREATE TABLE faces ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "image BLOB)";

        // create books table
        db.execSQL(CREATE_FACE_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS faces");

        // create fresh books table
        this.onCreate(db);
    }






}
