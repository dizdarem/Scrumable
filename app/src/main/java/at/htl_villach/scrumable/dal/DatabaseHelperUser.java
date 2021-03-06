package at.htl_villach.scrumable.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperUser extends SQLiteOpenHelper {
    // Table Name
    static final String TABLE_NAME = "Users";
    // Table columns
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String DATE = "date";

    private static final String DB_NAME = "HTL_VILLACH_SCRUMABLE_USERS.DB";
    private static final int DB_VERSION = 8;

    private static final String CREATE_TABLE_USER = "create table "+TABLE_NAME+" ( "+USERNAME
            + " TEXT NOT NULL UNIQUE, " + PASSWORD + " TEXT NOT NULL, " + DATE + " TEXT NOT NULL); ";

    DatabaseHelperUser(Context context){
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
