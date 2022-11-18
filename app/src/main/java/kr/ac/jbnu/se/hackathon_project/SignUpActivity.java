package kr.ac.jbnu.se.hackathon_project;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    EditText edt_SignUpStudentNumber;
    EditText edt_SignUpDepartment;
    EditText edt_SignUpName;
    EditText edt_SignUpPassword;
    Button btn_Signup;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        edt_SignUpStudentNumber = (EditText) findViewById(R.id.edt_SignUpStudentNumber);
        edt_SignUpDepartment = (EditText) findViewById(R.id.edt_SignUpDepartment);
        edt_SignUpName = (EditText) findViewById(R.id.edt_SignUpName);
        edt_SignUpPassword = (EditText) findViewById(R.id.edt_SignUpPassword);

        btn_Signup = (Button) findViewById(R.id.btn_Signup);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btn_Signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("기다려 주세요.");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(edt_SignUpStudentNumber.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "이미 가입하셨습니다.", Toast.LENGTH_LONG).show();
                        }

                        else
                        {
                            mDialog.dismiss();
                            UserInfo user = new UserInfo(edt_SignUpStudentNumber.getText().toString(),
                                    edt_SignUpPassword.getText().toString(), edt_SignUpName.getText().toString(),
                                    edt_SignUpDepartment.getText().toString(),0,false);
                            table_user.child(edt_SignUpStudentNumber.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "가입 성공하였습니다.", Toast.LENGTH_LONG).show();
                            Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}
