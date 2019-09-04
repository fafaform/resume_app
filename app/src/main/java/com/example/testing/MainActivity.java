package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    //////////////////////////////////
    // TODO local parameter cannot be used in different class, so, global (here) is one of the better place for parameter.
    // TODO private or public of parameter can be declare
    //////////////////////////////////
    Button resume;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////////////////////////////////
        // TODO link parameter to view in layout
        //////////////////////////////////
        resume = findViewById(R.id.view_info);
        register = findViewById(R.id.register);


        //////////////////////////////////
        // TODO set click function to "resume" button and "register" button
        //////////////////////////////////
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////
                // TODO Intent use to declare next activity that need to start
                // TODO startActivity is call to goto next Activity, so, make sure to finish everything before calling this line
                //////////////////////////////////
                Intent res = new Intent(getBaseContext(), ViewResumeActivity.class);
                startActivity(res);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(regis);
            }
        });
    }
}
