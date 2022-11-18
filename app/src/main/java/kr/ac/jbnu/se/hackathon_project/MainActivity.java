package kr.ac.jbnu.se.hackathon_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button soccer, basketball, footvolleyball, dodgeball, relay, tugofwar, wrestling, armwrestling, penaltyshootout;
    TextView departmentRank, individualRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        soccer = findViewById(R.id.btn_soccer);
        basketball = findViewById(R.id.btn_basketball);
        footvolleyball = findViewById(R.id.btn_footvolleyball);
        dodgeball = findViewById(R.id.btn_dodgeball);
        relay = findViewById(R.id.btn_relay);
        tugofwar = findViewById(R.id.btn_tugofwar);
        wrestling = findViewById(R.id.btn_wrestling);
        armwrestling = findViewById(R.id.btn_armwrestling);
        penaltyshootout = findViewById(R.id.btn_penaltyshootout);

        departmentRank = findViewById(R.id.txtV_departmentRank);
        individualRank = findViewById(R.id.txtV_individualRank);

        soccer.setOnClickListener(v->{
            Intent intent = new Intent(this, TimeChart.class);
            startActivity(intent);
        });


    }
}