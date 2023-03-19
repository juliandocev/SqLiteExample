package com.julidot.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    List<UserModel> usersList;
    ArrayAdapter<UserModel> usersArrayAdapter;
    DatabaseHelper dbHelper;
    SQLiteDatabase database;
    ListView listView;
    String emptyDatabaseStringMsg = "Базата от данни е празна!";
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        listView = findViewById(R.id.List);
        dbHelper = new DatabaseHelper(this);

        // Put the list to the list view
        showUsersList(dbHelper);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel user = (UserModel) parent.getItemAtPosition(position);
                dbHelper.delete(user);
                showUsersList(dbHelper);

                Toast.makeText(DataActivity.this, R.string.erased_user_toast, Toast.LENGTH_LONG).show();
            }
        });








    }

    private void showUsersList(DatabaseHelper dbHelper) {

        usersList = dbHelper.getAll();

        usersArrayAdapter =  new ArrayAdapter<UserModel>(DataActivity.this, android.R.layout.simple_list_item_1, usersList);

        listView.setAdapter(usersArrayAdapter);
    }

    @Override
    protected void onDestroy() {
        // затваряме връзката с БД
        database.close();
        dbHelper.close();
        super.onDestroy();
    }

}