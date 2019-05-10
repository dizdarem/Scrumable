package at.htl_villach.scrumable.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import at.htl_villach.scrumable.bll.BacklogItem;
import at.htl_villach.scrumable.bll.StatusEnum;
import at.htl_villach.scrumable.bll.User;

public class DatabaseManager {
    private Context context;
    private SQLiteDatabase database_BacklogItems;
    private SQLiteDatabase database_Users;

    public DatabaseManager(Context context ) {
        this.context = context;
    }

    public void open() throws SQLException {
        DatabaseHelperBacklogItem databaseHelperBacklogItem = new DatabaseHelperBacklogItem(this.context);
        DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(this.context);
        database_BacklogItems = databaseHelperBacklogItem.getWritableDatabase();
        database_Users = databaseHelperUser.getWritableDatabase();
    }

    public ArrayList<BacklogItem> fetch_BacklogItem(int paramBacklogItemId, StatusEnum paramStatus){
        ArrayList<BacklogItem> list = new ArrayList<>();
        String[] columns = new String[]{ "_id", DatabaseHelperBacklogItem.TITLE, DatabaseHelperBacklogItem.DESCRIBTION, DatabaseHelperBacklogItem.STATUS, DatabaseHelperBacklogItem.USER};

        String where = null;
        String[] whereArgs = null;

        if(paramBacklogItemId != 0) {
            where = "id=?";
            whereArgs = new String[]{String.valueOf(paramBacklogItemId)};
        } else {
            if(paramStatus != null) {
                where = "status=?";
                whereArgs = new String[]{paramStatus.toString()};
            }
        }

        Cursor cursor = database_BacklogItems.query(DatabaseHelperBacklogItem.TABLE_NAME, columns, where, whereArgs, null, null, null, null );

        if( cursor != null){
            while(cursor.moveToNext()){
                String curStatusEnumAsString = cursor.getString(3);
                StatusEnum curStatusEnumAsEnum = StatusEnum.valueOf(curStatusEnumAsString);

                ArrayList<User> curUserList = fetch_Users(cursor.getString(4));
                User curUser = curUserList.get(0);

                BacklogItem curBacklogItem = new BacklogItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), curStatusEnumAsEnum, curUser);
                list.add(curBacklogItem);
            }
            cursor.close();
        }
        return list;
    }

    public ArrayList<User> fetch_Users(String paramUsername){
        ArrayList<User> list = new ArrayList<>();
        String[] columns = new String[]{DatabaseHelperUser.USERNAME, DatabaseHelperUser.PASSWORD, DatabaseHelperUser.DATE};

        String where = null;
        String[] whereArgs = null;

        if(paramUsername != null) {
            where = "username=?";
            whereArgs = new String[]{paramUsername};
        }

        Cursor cursor = database_Users.query(DatabaseHelperUser.TABLE_NAME, columns, where, whereArgs, null, null, null, null );

        if( cursor != null){
            while(cursor.moveToNext()){
                String curDateAsString = cursor.getString(2);
                Date curDateAsDate = null;
                try {
                    curDateAsDate = new SimpleDateFormat("dd.MM.yyyy").parse(curDateAsString.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                User curUser = new User(cursor.getString(0), cursor.getString(1), curDateAsDate);
                list.add(curUser);
            }
            cursor.close();
        }
        return list;
    }

    public void insert_BacklogItem(BacklogItem paramBacklogItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperBacklogItem.TITLE, paramBacklogItem.getTitle());
        contentValues.put(DatabaseHelperBacklogItem.DESCRIBTION, paramBacklogItem.getDescribtion());
        contentValues.put(DatabaseHelperBacklogItem.STATUS, paramBacklogItem.getStatus().toString());
        contentValues.put(DatabaseHelperBacklogItem.USER, paramBacklogItem.getEditor().toString());
        database_BacklogItems.insert(DatabaseHelperBacklogItem.TABLE_NAME, null, contentValues );
    }

    public void insert_User(User paramUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperUser.USERNAME, paramUser.getUsername());
        contentValues.put(DatabaseHelperUser.PASSWORD, paramUser.getPassword());
        contentValues.put(DatabaseHelperUser.DATE, paramUser.getDate().toString());
        database_Users.insert(DatabaseHelperUser.TABLE_NAME, null, contentValues );
    }

    public void delete_BacklogItem(int id) {
        database_BacklogItems.delete(DatabaseHelperBacklogItem.TABLE_NAME, DatabaseHelperBacklogItem.ID + "=?", new String[]{String.valueOf(id)});
    }

    public void delete_User(String username) {
        database_Users.delete(DatabaseHelperUser.TABLE_NAME, DatabaseHelperUser.USERNAME + "=?", new String[]{username});
    }

    public int update_BacklogItem(BacklogItem paramBacklogItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperBacklogItem.TITLE, paramBacklogItem.getTitle());
        contentValues.put(DatabaseHelperBacklogItem.DESCRIBTION, paramBacklogItem.getDescribtion());
        contentValues.put(DatabaseHelperBacklogItem.STATUS, paramBacklogItem.getStatus().toString());
        contentValues.put(DatabaseHelperBacklogItem.USER, paramBacklogItem.getEditor().toString());
        int nrOfUpdatedRows = database_BacklogItems.update(DatabaseHelperBacklogItem.TABLE_NAME, contentValues, DatabaseHelperBacklogItem.ID + "=?", new String[]{String.valueOf(paramBacklogItem.getId())});
        return nrOfUpdatedRows;
    }

    public int update_User(User paramUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperUser.USERNAME, paramUser.getUsername());
        contentValues.put(DatabaseHelperUser.PASSWORD, paramUser.getPassword());
        contentValues.put(DatabaseHelperUser.DATE, paramUser.getDate().toString());
        int nrOfUpdatedRows = database_Users.update(DatabaseHelperUser.TABLE_NAME, contentValues, DatabaseHelperUser.USERNAME + "=?", new String[]{paramUser.getUsername()});
        return nrOfUpdatedRows;
    }
}
