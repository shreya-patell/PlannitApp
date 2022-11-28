package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {
    DBHelper DB;
    EditText displayUsername;
    Button signOutButton;
    FloatingActionButton createEventBtn;
    String[][] events;
    TextView finalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DB = new DBHelper(this);
        signOutButton = findViewById(R.id.signOutButton);
        createEventBtn = findViewById(R.id.createEventBtn);

        User currentUser = (User) getApplicationContext();
        String username = currentUser.getCurrentUser();


        events = DB.getEvents(username);
        for (int i = 0; i < events.length; i++) {
            printDetails(events[i][0], events[i][1], events[i][2], events[i][3], events[i][4], events[i][5], events[i][6]);
        }

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, EventDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void printDetails(String name, String desc, String date, String startTime, String endTime, String location, String invites) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        TextView eventDetailsText = new TextView(this);
        LinearLayout linearLayout1 = findViewById(R.id.eventDetailsLayout);

        eventDetailsText.setLayoutParams(params);
        eventDetailsText.setText("Event Name: " + name + "\n" + "Event Description: " +  desc + "\n" + "Event Date: " +  date + "\n" + "Event Start Time: " + startTime + "\n" + "Event End Time: " + endTime + "\n" + "Event Location: " +  location + "\n" + "Invites: " + invites + "\n");
        linearLayout.addView(eventDetailsText);
        linearLayout1.addView(linearLayout);
    }
}