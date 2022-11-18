package kr.ac.jbnu.se.hackathon_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
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
import com.google.firebase.firestore.auth.User;

public class TimeChart extends AppCompatActivity {

    private ListViewAdapter listViewAdapter;
    public UserInfo userInfo;
    ListView listView;
    FirebaseDatabase db;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timechart);

        Intent getIntent = getIntent();
        UserInfo userInfo = (UserInfo) getIntent.getSerializableExtra("userInfo");
        listViewAdapter = new ListViewAdapter();
        listView = findViewById(R.id.listView);

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                if(LoginActivity.getIsManager()){
                    Toast.makeText(TimeChart.this,"dd",Toast.LENGTH_LONG).show();
                    intent = new Intent(TimeChart.this, ManagerActivity.class);
                    startActivity(intent);
                }
                else{
                  intent = new Intent(TimeChart.this, Match_Predict.class);
                  MatchData matchData = listViewAdapter.getItem(i);
                  intent.putExtra("matchData", matchData);
                  intent.putExtra("userInfo", userInfo);
                  startActivity(intent);
                }

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
                    MatchData matchData = dataSnapshot.getValue(MatchData.class);
                    matchData.setKey(dataSnapshot.getKey());

                    listViewAdapter.add(matchData);
                }

                listViewAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
