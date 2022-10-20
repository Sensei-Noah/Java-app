package com.example.javaprojectnojus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login();
        register();
    }


    private boolean isValid(String credentials){
        final String CREDENTIALS_PATTERN = "^[a-zA-Z0-9]{3,20}+$";

        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);

        return matcher.matches();
    }
    public void openMainPage(){
        Intent mainActivity = new Intent(this, MainPage.class);
        startActivity(mainActivity);
    }

    public void openRegisterPage(){
        Intent registerActivity = new Intent(this, Register.class);
        startActivity(registerActivity);
    }

    private void register(){
        MaterialButton registerbtn =(MaterialButton) findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(view -> {
            openRegisterPage();
        });
    }

    private void login(){
        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        CheckBox rememberMeCheckBox = (CheckBox) findViewById(R.id.login_remember_me);
        final User user = new User(getApplicationContext());
        rememberMeCheckBox.setChecked(user.isRemembered());

        //check if user pressed remember me checkbox
        if(user.isRemembered()){
            username.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
            password.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        }else{
            username.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }

        loginbtn.setOnClickListener(view -> {

            String username2 = username.getText().toString();
            String password2 = password.getText().toString();

            boolean cancel = false;


            if(!isValid(username2)){
                username.setError("Username is invalid!");
                view = username;
                cancel = true;
            }
            if(!isValid(password2)){
                password.setError("Password is invalid!");
                view = password;
                cancel = true;
            }

            if(cancel){
                view.requestFocus();
            }else{
                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                if(rememberMeCheckBox.isChecked()){
                    user.setUsernameForLogin(username2);
                    user.setPasswordForLogin(password2);
                    user.setRemembered(true);
                }else{
                    user.setUsernameForLogin(username2);
                    user.setPasswordForLogin(password2);
                    user.setRemembered(false);
                }

                openMainPage();
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
}