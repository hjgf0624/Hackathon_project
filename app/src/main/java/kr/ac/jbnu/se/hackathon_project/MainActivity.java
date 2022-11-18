package kr.ac.jbnu.se.hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {

    Button soccer, basketball, footvolleyball, dodgeball, relay, tugofwar, wrestling, armwrestling, penaltyshootout;
    TextView individualScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = getIntent();
        UserInfo userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
        soccer = findViewById(R.id.btn_soccer);
        basketball = findViewById(R.id.btn_basketball);
        footvolleyball = findViewById(R.id.btn_footvolleyball);
        dodgeball = findViewById(R.id.btn_dodgeball);
        relay = findViewById(R.id.btn_relay);
        tugofwar = findViewById(R.id.btn_tugofwar);
        wrestling = findViewById(R.id.btn_wrestling);
        armwrestling = findViewById(R.id.btn_armwrestling);
        penaltyshootout = findViewById(R.id.btn_penaltyshootout);

        individualScore = findViewById(R.id.txtV_individualScore);

        soccer.setOnClickListener(v->{
            Intent soccerIntent = new Intent(this, TimeChart.class);
            soccerIntent.putExtra("userInfo", userInfo);
            startActivity(soccerIntent);
        });


    }
}
