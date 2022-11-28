package com.example.anson_tu__100655482__mobile_final_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventInvitesActivity extends AppCompatActivity {
    EditText inviteNameField;
    Button inviteUserBtn, backToMapBtn, goToReviewBtn;
    TextView invitesList;
    ArrayList<String> listOfUsers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_invites);

        inviteNameField = findViewById(R.id.inviteNameField);
        inviteUserBtn = findViewById(R.id.inviteUserBtn);
        backToMapBtn = findViewById(R.id.backToMapBtn);
        goToReviewBtn = findViewById(R.id.goToReviewBtn);
        invitesList = findViewById(R.id.invitesList);

        inviteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inviteNameField.getText().toString();
                if (!name.isEmpty()) {
                    String listOfInvites = "";
                    listOfUsers.add(name + "\n");
                    for (int i = 0; i < listOfUsers.size(); i++) {
                        listOfInvites += listOfUsers.get(i);
                    }
                    invitesList.setText(listOfInvites);
                    inviteNameField.setText("");
                }
            }
        });

        backToMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goToReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventInvitesActivity.this, EventReviewActivity.class);

                String listOfInvites = "";
                for (int i = 0; i < listOfUsers.size(); i++) {
                    listOfInvites += listOfUsers.get(i);
                }

                intent.putExtra("keyEventName", getIntent().getStringExtra("keyEventName"));
                intent.putExtra("keyEventDescription", getIntent().getStringExtra("keyEventDescription"));
                intent.putExtra("keyEventDate", getIntent().getStringExtra("keyEventDate"));
                intent.putExtra("keyEventStartTime", getIntent().getStringExtra("keyEventStartTime"));
                intent.putExtra("keyEventEndTime", getIntent().getStringExtra("keyEventEndTime"));
                intent.putExtra("keyEventLocation", getIntent().getStringExtra("keyEventLocation"));
                intent.putExtra("keyEventInvites", listOfInvites);

                startActivity(intent);
            }
        });
    }
}