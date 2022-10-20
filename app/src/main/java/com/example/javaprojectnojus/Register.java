package com.example.javaprojectnojus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        register();

    }

    private void register(){
        MaterialButton regbtn = (MaterialButton) findViewById(R.id.signupbtn);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        TextView repassword =(TextView) findViewById(R.id.repassword);
        TextView email =(TextView) findViewById(R.id.email);



        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                String email2 = email.getText().toString();
                String repassword2 = repassword.getText().toString();


                EditText username = (EditText) findViewById(R.id.username);


                String username1 = username.getText().toString();
                Toast.makeText(Register.this, "Username is "+username1, Toast.LENGTH_SHORT);

                boolean cancel = false;

                if(!isValidUsername(username2)){
                    username.setError("Username is invalid!");
                    view = username;
                    cancel = true;
                }
                if(!isValidPassword(password2)){
                    password.setError("Password is invalid!");
                    view = password;
                    cancel = true;
                }
                if(!isValidEmail(email2)){
                    email.setError("Email is invalid!");
                    view = email;
                    cancel = true;
                }
                if(!isValidPassword(repassword2)){
                    repassword.setError("Password is invalid!");
                    view = repassword;
                    cancel = true;
                }
                if(cancel){
                    view.requestFocus();
                }else{
                    Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    private boolean isValidUsername(String credentials){
        final String CREDENTIALS_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }

    private boolean isValidEmail(String credentials){
        final String CREDENTIALS_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }

    private boolean isValidPassword(String credentials){
        final String CREDENTIALS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }
}