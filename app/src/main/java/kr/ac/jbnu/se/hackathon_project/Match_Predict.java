package kr.ac.jbnu.se.hackathon_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Match_Predict extends AppCompatActivity {

    private TextView event;
    private TextView player;
    private TextView place;
    private TextView time;
    private Button player1_win_btn;
    private Button player2_win_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_predict);

        Intent intent = getIntent();
        MatchData matchData = (MatchData) intent.getSerializableExtra("matchData");

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
    }
}
