package com.example.finalprojectbcs430W;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_main);
    }

    public void onClick(View v) {
        Intent myIntent = new Intent(MainHomePage.this, FindFriend.class);
        MainHomePage.this.startActivity(myIntent);
        finish();
    }
}
