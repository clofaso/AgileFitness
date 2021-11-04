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
        switch (v.getId()){
            case R.id.card1:{
                Intent myIntent = new Intent(MainHomePage.this, WorkoutLog.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card2:{
                Intent myIntent = new Intent(MainHomePage.this, NutritionLog.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card3:{
                Intent myIntent = new Intent(MainHomePage.this, FindFriend.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card4:{
                Intent myIntent = new Intent(MainHomePage.this, Calendar.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card5:{
                Intent myIntent = new Intent(MainHomePage.this, FindPersonalTrainer.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
            case R.id.card6:{
                Intent myIntent = new Intent(MainHomePage.this, Dashboard.class);
                MainHomePage.this.startActivity(myIntent);
                break;
            }
        }
    }
}
