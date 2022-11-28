package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class EventDetailsActivity extends AppCompatActivity {
    EditText eventNameField, eventDescriptionField;
    DatePicker eventDatePicker;
    TimePicker eventStartTimePicker, eventEndTimePicker;
    Button backToHomeBtn, goToMapBtn;

    String eventName = "";
    String eventDescription = "";
    String eventDate = "";
    String eventStartTime = "";
    String eventEndTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventNameField = findViewById(R.id.eventNameField);
        eventDescriptionField = findViewById(R.id.eventDescriptionField);
        eventDatePicker = findViewById(R.id.eventDatePicker);
        eventStartTimePicker = findViewById(R.id.eventStartTimePicker);
        eventEndTimePicker = findViewById(R.id.eventEndTimePicker);
        backToHomeBtn = findViewById(R.id.backToHomeBtn);
        goToMapBtn = findViewById(R.id.goToMapBtn);

        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goToMapBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int day = eventDatePicker.getDayOfMonth();
                int month = eventDatePicker.getMonth() + 1;
                int year = eventDatePicker.getYear();
                int startTimeHour = eventStartTimePicker.getHour();
                int startTimeMinute = eventStartTimePicker.getMinute();
                int endTimeHour = eventEndTimePicker.getHour();
                int endTimeMinute = eventEndTimePicker.getMinute();

                eventName = eventNameField.getText().toString();
                eventDescription = eventDescriptionField.getText().toString();
                eventDate = day + "-" + month + "-" + year;
                eventStartTime = startTimeMinute > 10 ? startTimeHour + ":" + startTimeMinute : startTimeHour + ":" + "0" + startTimeMinute;
                eventEndTime = endTimeMinute > 10 ? endTimeHour + ":" + endTimeMinute : endTimeHour + ":" + "0" + endTimeMinute;

                Intent intent = new Intent(EventDetailsActivity.this, MapsActivity.class);
                intent.putExtra("keyEventName", eventName);
                intent.putExtra("keyEventDescription", eventDescription);
                intent.putExtra("keyEventDate", eventDate);
                intent.putExtra("keyEventStartTime", eventStartTime);
                intent.putExtra("keyEventEndTime", eventEndTime);

                startActivity(intent);
            }
        });
    }
}