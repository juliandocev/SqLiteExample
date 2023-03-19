package com.julidot.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText etUserName;
    EditText etUserAge;
    SwitchCompat switchIsActive;
    Button btnSave;
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.etUserName);
        etUserAge = findViewById(R.id.etUserAge);
        switchIsActive = findViewById(R.id.switchIsActive);
        btnSave = findViewById(R.id.btnSave);
        btnList = findViewById(R.id.btnList);





        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUserName.getText().toString().trim();
                int age = Integer.parseInt(etUserAge.getText().toString());
                boolean isActive = switchIsActive.isChecked();

                UserModel userModel = new UserModel(-1, username, age, isActive);
                try {
                    Toast.makeText(MainActivity.this,userModel.toString(),Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }

                dbHelper = new DatabaseHelper(MainActivity.this);
                boolean isSuccess = dbHelper.insert(userModel);
                Toast.makeText(MainActivity.this, "Creation= " + isSuccess,Toast.LENGTH_LONG).show();

            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });





    }

    @Override
    protected void onDestroy() {
        // close the helper
        dbHelper.close();
        super.onDestroy();
    }




}
