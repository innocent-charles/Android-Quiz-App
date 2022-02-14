package com.nyakana.quizapp_CP_313;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.nyakana.quizapp_CP_313.User.LoginActivity;

public class splash_one extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
