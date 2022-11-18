package kr.ac.jbnu.se.hackathon_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimeChart extends AppCompatActivity {

    private ListViewAdapter listViewAdapter;
    ListView listView;
    FirebaseDatabase db;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timechart);

        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.listView);

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TimeChart.this, Match_Predict.class);
                MatchData matchData = listViewAdapter.getItem(i);
                intent.putExtra("matchData", matchData);
                startActivity(intent);
            }
        });

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference().child("Match");

        getValue();
    }

    private void getValue(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String event = dataSnapshot.child("event").getValue(String.class);
                    String player1 = dataSnapshot.child("player1").getValue(String.class);
                    String player2 = dataSnapshot.child("player2").getValue(String.class);
                    String place = dataSnapshot.child("place").getValue(String.class);
                    String time = dataSnapshot.child("time").getValue(String.class);

                    listViewAdapter.add(event, player1, player2, place, time);
                }

                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}