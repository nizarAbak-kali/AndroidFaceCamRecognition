package fr.p8.m2ise.androidfacecamrecog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {


    public static class PostsDatabaseHelper extends SQLiteOpenHelper {
        // Database Info
        private static final String DATABASE_NAME = "postsDatabase";
        private static final int DATABASE_VERSION = 1;

        // Table Names
        private static final String TABLE_POSTS = "posts";
        private static final String TABLE_USERS = "users";

        // Post Table Columns
        private static final String KEY_POST_ID = "id";
        private static final String KEY_POST_USER_ID_FK = "userId";
        private static final String KEY_POST_TEXT = "text";

        // User Table Columns
        private static final String KEY_USER_ID = "id";
        private static final String KEY_USER_NAME = "userName";
        private static final String KEY_USER_PROFILE_PICTURE_URL = "profilePictureUrl";


        private static PostsDatabaseHelper sInstance;
        public String text;

        public static synchronized PostsDatabaseHelper getInstance(Context context) {
            // Use the application context, which will ensure that you
            // don't accidentally leak an Activity's context.
            // See this article for more information: http://bit.ly/6LRzfx
            if (sInstance == null) {
                sInstance = new PostsDatabaseHelper(context.getApplicationContext());
            }
            return sInstance;
        }

        public PostsDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Called when the database connection is being configured.
        // Configure database settings for things like foreign key support, write-ahead logging, etc.
        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }

        // Called when the database is created for the FIRST time.
        // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS +
                    "(" +
                    KEY_POST_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                    KEY_POST_USER_ID_FK + " INTEGER REFERENCES " + TABLE_USERS + "," + // Define a foreign key
                    KEY_POST_TEXT + " TEXT" +
                    ")";

            String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                    "(" +
                    KEY_USER_ID + " INTEGER PRIMARY KEY," +
                    KEY_USER_NAME + " TEXT," +
                    KEY_USER_PROFILE_PICTURE_URL + " TEXT" +
                    ")";
            db.execSQL(CREATE_POSTS_TABLE);
            db.execSQL(CREATE_USERS_TABLE);
        }

        // Called when the database needs to be upgraded.
        // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
        // but the DATABASE_VERSION is different than the version of the database that exists on disk.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion != newVersion) {
                // Simplest implementation is to drop all old tables and recreate them
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
                onCreate(db);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }
}
