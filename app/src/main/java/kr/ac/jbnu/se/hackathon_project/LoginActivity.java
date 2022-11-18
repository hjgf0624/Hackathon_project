package kr.ac.jbnu.se.hackathon_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private static boolean isManager;
    EditText edt_SignInStudentNumber;
    EditText edt_SignInPassword;
    Button btn_login;
    Button btn_join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        isManager = false;
        edt_SignInStudentNumber = (EditText) findViewById(R.id.edt_SignUpStudentNumber);
        edt_SignInPassword = (EditText) findViewById(R.id.edt_SignUpPassword);
        btn_login = (Button) findViewById(R.id.btn_SignIn);
        btn_join = (Button) findViewById(R.id.btn_join);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("기다려 주세요.");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(edt_SignInStudentNumber.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            UserInfo user = snapshot.child(edt_SignInStudentNumber.getText().toString()).getValue(UserInfo.class);
                            isManager = user.getIsmanager();
                            if(user.getPassword().equals(edt_SignInPassword.getText().toString()))
                            {

                                Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                                homeIntent.putExtra("userInfo", user);
                                startActivity(homeIntent);
                                finish();
                            }


                            else
                            {
                                Toast.makeText(LoginActivity.this,"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "계정이 없거나 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(SignUpIntent);
                finish();
            }
        });
    }

    public static boolean getIsManager(){
        return isManager;
    }
}
