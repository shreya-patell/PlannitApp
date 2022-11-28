package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventReviewActivity extends AppCompatActivity {
    DBHelper DB;
    String eventName, eventDescription, eventDate, eventStartTime, eventEndTime, eventLocation, eventInvites;
    TextView name, description, date, startTime, endTime, location, listOfInvites;
    Button backToInvitesBtn, submitEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_review);

        DB = new DBHelper(this);

        eventName = getIntent().getStringExtra("keyEventName");
        eventDescription = getIntent().getStringExtra("keyEventDescription");
        eventDate = getIntent().getStringExtra("keyEventDate");
        eventStartTime = getIntent().getStringExtra("keyEventStartTime");
        eventEndTime = getIntent().getStringExtra("keyEventEndTime");
        eventLocation = getIntent().getStringExtra("keyEventLocation");
        eventInvites = getIntent().getStringExtra("keyEventInvites");

        name = findViewById(R.id.nameField);
        description = findViewById(R.id.descriptionField);
        date = findViewById(R.id.dateField);
        startTime = findViewById(R.id.startTimeField);
        endTime = findViewById(R.id.endTimeField);
        location = findViewById(R.id.locationField);
        listOfInvites = findViewById(R.id.invitesField);
        backToInvitesBtn = findViewById(R.id.backToInvitesBtn);
        submitEventBtn = findViewById(R.id.submitEventBtn);

        name.setText(eventName);
        description.setText(eventDescription);
        date.setText(eventDate);
        startTime.setText(eventStartTime);
        endTime.setText(eventEndTime);
        location.setText(eventLocation);
        listOfInvites.setText(eventInvites);

        backToInvitesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User currentUser = (User) getApplicationContext();
                String username = currentUser.getCurrentUser();
                Boolean eventCreated = DB.insertEventData(
                        eventName, eventDescription, eventDate, eventStartTime, eventEndTime, eventLocation, eventInvites, username
                );
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}