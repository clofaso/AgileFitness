package com.example.finalprojectbcs430W;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = findViewById(R.id.calendarView);
    }

    public void Currentdate(View view) {
        long time = System.currentTimeMillis();
        calendar.setDate(time,false,true);
    }
}