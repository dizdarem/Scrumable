package at.htl_villach.scrumable.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperBacklogItem extends SQLiteOpenHelper {
    // Table Name
    static final String TABLE_NAME = "BacklogItems";
    // Table columns
    static final String ID = "_id";
    static final String TITLE = "title";
    static final String DESCRIBTION = "describtion";
    static final String STATUS = "status";
    static final String USER = "user";

    private static final String DB_NAME = "HTL_VILLACH_SCRUMABLE_BACKLOGITEMS.DB";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_CONCERT = "create table "+TABLE_NAME+" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE
            + " TEXT NOT NULL UNIQUE, " + DESCRIBTION + " TEXT NOT NULL, " + STATUS + " TEXT NOT NULL, " + USER + " TEXT NOT NULL); ";

    DatabaseHelperBacklogItem(Context context){
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_CONCERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
