package kr.ac.jbnu.se.hackathon_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class Match_Predict extends AppCompatActivity {

    private TextView event;
    private TextView player;
    private TextView place;
    private TextView time;
    private Button player1_win_btn;
    private Button player2_win_btn;

    FirebaseDatabase db;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_predict);

        Intent getIntent = getIntent();
        MatchData matchData = (MatchData) getIntent.getSerializableExtra("matchData");
        UserInfo userInfo = (UserInfo) getIntent.getSerializableExtra("userInfo");

        event = findViewById(R.id.event);
        player = findViewById(R.id.player);
        place = findViewById(R.id.place);
        time = findViewById(R.id.time);
        player1_win_btn = findViewById(R.id.player1_win_btn);
        player2_win_btn = findViewById(R.id.player2_win_btn);

        event.setText(matchData.getEvent());
        player.setText(matchData.getPlayer1() + "vs" + matchData.getPlayer2());
        place.setText(matchData.getPlace());
        time.setText(matchData.getTime());
        player1_win_btn.setText(matchData.getPlayer1());
        player2_win_btn.setText(matchData.getPlayer2());

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("Match/"+matchData.getKey());

        player1_win_btn.setOnClickListener(new View.OnClickListener() {
            final DatabaseReference prediction_ref = myRef.child("prediction");
            @Override
            public void onClick(View view) {
                prediction_ref.child("player2_win").child(userInfo.getStudent_Number()).removeValue();
                prediction_ref.child("player1_win").child(userInfo.getStudent_Number()).setValue(userInfo.getDepartment());
            }
        });

        player2_win_btn.setOnClickListener(new View.OnClickListener() {
            final DatabaseReference prediction_ref = myRef.child("prediction");
            @Override
            public void onClick(View view) {
                prediction_ref.child("player1_win").child(userInfo.getStudent_Number()).removeValue();
                prediction_ref.child("player2_win").child(userInfo.getStudent_Number()).setValue(userInfo.getDepartment());
            }
        });
    }
}
