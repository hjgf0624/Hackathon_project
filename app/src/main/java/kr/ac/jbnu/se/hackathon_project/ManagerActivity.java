package kr.ac.jbnu.se.hackathon_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ManagerActivity extends AppCompatActivity {
    Button player1,player2;

    FirebaseDatabase db;
    DatabaseReference myRef;
    DatabaseReference userRef;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);
        Intent getIntent = getIntent();
        MatchData matchData = (MatchData) getIntent.getSerializableExtra("matchData");

        player1 = findViewById(R.id.btn_player1);
        player2 = findViewById(R.id.btn_player2);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("Match").child(matchData.getKey()).child("prediction");
        userRef = db.getReference().child("User");

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arrayList = new ArrayList<>();
                myRef.child("player1_win").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()){
                            arrayList.add(dataSnapshot.getKey());
                            //userRef.child(dataSnapshot.getKey()).child("score").setValue(ServerValue.increment(3));
                        }
                        for(String user : arrayList){
                            Toast.makeText(ManagerActivity.this, user, Toast.LENGTH_SHORT).show();
                            userRef.child(user).child("score").setValue(ServerValue.increment(3));
                        }
                    }
                });
            }
        });

        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arrayList = new ArrayList<>();
                myRef.child("player2_win").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        for (DataSnapshot dataSnapshot : task.getResult().getChildren()){
                            arrayList.add(dataSnapshot.getKey());
                            //userRef.child(dataSnapshot.getKey()).child("score").setValue(ServerValue.increment(3));
                        }
                        for(String user : arrayList){
                            Toast.makeText(ManagerActivity.this, user, Toast.LENGTH_SHORT).show();
                            userRef.child(user).child("score").setValue(ServerValue.increment(3));
                        }
                    }
                });
            }
        });
    }
}
