package com.example.proiect_android_ionanamaria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AddUser extends AppCompatActivity {

    EditText etUserName;
    Button btnAddUser;
    Spinner spinnerRank;
    User user= new User();
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);


        etUserName=findViewById(R.id.etUserName);
        spinnerRank=findViewById(R.id.spinnerRank);
        String[] rankArray = {"Student", "Admin", "Prossor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, rankArray);//xml support created
        spinnerRank.setAdapter(adapter);
        btnAddUser=findViewById(R.id.btnAddUser);



        users=(ArrayList<User>) getIntent().getSerializableExtra("users");


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGol()){
                    Toast.makeText(AddUser.this, "Error! Complete the your data! ", Toast.LENGTH_SHORT).show();
                }else{

                    user.setUsername(etUserName.getText().toString());
                    user.setRank(spinnerRank.getSelectedItem().toString());
                    if(users!=null && users.contains(user)){
                        Toast.makeText(AddUser.this, "User Already in list", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent= new Intent();
                        intent.putExtra("user",user);
                        setResult(RESULT_OK,intent);
                        users.add(user);
                        Toast.makeText(AddUser.this,  user.toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });




    }

    boolean isGol(){
       if(etUserName.getText().toString().isEmpty()){
            return true;

        }else{
            return  false;
        }

    }
}