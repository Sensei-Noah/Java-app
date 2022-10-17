package com.example.javaprojectnojus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(view -> {

            boolean cancel = false;


            if(!isValid(username.getText().toString())){
                username.setError("Username is invalid!");
                cancel = true;
            }
            if(!isValid(password.getText().toString())){
                password.setError("Password is invalid!");
                cancel = true;
            }
            if(cancel){
                view.requestFocus();
            }else{
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                openActivity2();
            }

//                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                    //success
//                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                    openActivity2();
//                }else{
//                    //fail
//                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
//
//                }
        });
    }


    private boolean isValid(String credentials){
        final String CREDENTIALS_PATTERN = "^[a-zA-Z0-9]{3,15}+$";

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}