package com.julidot.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="users.db";
    private static final int DATABASE_VERSION = 1;

    // table name
    public final static String TABLE_NAME = "users";
    // user id
    public final static String UID = "id";
    // user name
    public final static String COLUMN_USER_NAME = "name";
    // user age
    public final static String COLUMN_USER_AGE = "age";
    // user status
    public final static String COLUMN_USER_STATUS = "status";

    // constructor
    public DatabaseHelper( @Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * this is called the first time a database is accessed
     * here is the code for the database creation
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ITEMS_TABLE =
                "CREATE TABLE "+ TABLE_NAME + " " +
                        "("+ UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_USER_NAME + " TEXT NOT NULL, "
                        + COLUMN_USER_AGE + " INT, "
                        + COLUMN_USER_STATUS + " BOOL);";

        // изпълнение на SQL оператора
        db.execSQL(SQL_CREATE_ITEMS_TABLE);

    }

    /**
     * this is called if the database version number changes
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete the old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        // create the new table
        onCreate(db);

    }

    public boolean insert( UserModel userModel){

        SQLiteDatabase database = this.getWritableDatabase();
        // Create an object ContentValues, where the name of the column is the key and the tribute is the value
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, userModel.getUserName());
        cv.put(COLUMN_USER_AGE, userModel.getUserAge());
        cv.put(COLUMN_USER_STATUS, userModel.isActive());
        long insert = database.insert(TABLE_NAME, null, cv);

        // close the database
        database.close();

        if(insert ==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean delete( UserModel userModel){

        String query = " DELETE FROM " + TABLE_NAME + " WHERE " + UID + " = " + userModel.getIdUser();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);



        if(cursor.moveToFirst()){
            // Close the cursor and the database
            cursor.close();
            database.close();
            return true;
        }else{
            // Close the cursor and the database
            cursor.close();
            database.close();
            return false;
        }


    }

    public List<UserModel> getAll(){

        List<UserModel> list = new ArrayList<>();

        // get the data from the database

        String query = " SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase database =  this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            // loop through the cursor and create customer objects. Put them into the return list
            do {
                int userId = cursor.getInt(0);
                String userName = cursor.getString(1);
                int userAge = cursor.getInt(2);
                boolean status = cursor.getInt(3) ==1 ?true: false;
                UserModel newUser =  new UserModel(userId, userName, userAge, status);
                list.add(newUser);

            }while (cursor.moveToNext());

        }
        // Close the cursor and the database
        cursor.close();
        database.close();
        return list;
    }


}
