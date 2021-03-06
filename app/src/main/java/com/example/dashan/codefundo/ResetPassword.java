package com.example.dashan.codefundo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    EditText emailfield;
    TextView result;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

       emailfield = (EditText)findViewById(R.id.resetemailid);
        Button back =(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendresetlink();
            }
        });
    }
    private void sendresetlink() {
        String email = emailfield.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailfield.setError("Enter  a valid email id");
            emailfield.requestFocus();
            return;
        }
        FirebaseAuth mauth = FirebaseAuth.getInstance();
        // result.setEnabled(true);
        mauth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               if(task.isSuccessful()){

                                                   //result.setText("Email has been send successfully");
                                                   Toast.makeText(getApplicationContext(),"Email has been send SuccessFully",Toast.LENGTH_SHORT).show();
                                                   emailfield.setText("");
                                                   emailfield.setEnabled(false);
                                                   submit.setEnabled(false);
                                               }
                                               else{
                                                   Toast.makeText(getApplicationContext(),"try Again later",Toast.LENGTH_SHORT).show();
                                                   //result.setText("Try again later");
                                               }
                                           }
                                       }
                );
    }
}
