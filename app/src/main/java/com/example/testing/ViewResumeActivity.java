package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ViewResumeActivity extends AppCompatActivity {

    FileInputStream inputStream;
    ArrayList<String> list_file;
    ViewGroup view_main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        //////////////////////////////////
        //link parameter to view in layout
        //////////////////////////////////
        view_main_layout = (ViewGroup) findViewById(R.id.view_main_layout);


        //////////////////////////////////
        //Declare parameter
        //////////////////////////////////
        list_file = new ArrayList<>();


        //////////////////////////////////
        // TODO create file if not exist
        // try catch use for handle exception error
        // other then "catch", "finally" is possible to use
        //////////////////////////////////
        try {
            //////////////////////////////////
            // open file
            // Global is a public class like Utility class
            //////////////////////////////////
            inputStream = openFileInput(Global.resume_list_file);
        } catch (FileNotFoundException fe){
            //////////////////////////////////
            // nest try catch is possibled
            //////////////////////////////////
            try {
                //////////////////////////////////
                // use file to create new file if not exist
                //////////////////////////////////
                File file = new File(getApplicationContext().getFilesDir(), Global.resume_list_file);
                file.createNewFile();
            } catch (IOException e) {
                //////////////////////////////////
                // printStackTrace() is used to print error occur without crashing the program
                //////////////////////////////////
                e.printStackTrace();
            }
            //////////////////////////////////
            // more than a catch can be handled
            //////////////////////////////////
        } catch (Exception e){
            e.printStackTrace();
        }


        //////////////////////////////////
        // Different comment color
        // TODO get all file list
        //////////////////////////////////
        // FIXME is another type of comment showing to you
        //////////////////////////////////
        try {
//            inputStream = openFileInput(Global.resume_list_file);

            //////////////////////////////////
            // bufferedReader is used to read text from a file
            //////////////////////////////////
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                list_file.add(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        //////////////////////////////////
        // calling function
        //////////////////////////////////
        createCard();
    }

    private void createCard(){


        //////////////////////////////////
        // create views manually using JAVA
        //////////////////////////////////
        try {
            //////////////////////////////////
            // create layout property
            //////////////////////////////////
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20, 10, 20, 10);

            //////////////////////////////////
            // loop to print all resume registered
            //////////////////////////////////
            for (int i = 0; i < list_file.size(); i++) {
                //////////////////////////////////
                // create container of other views
                //////////////////////////////////
                LinearLayout card = new LinearLayout(getBaseContext());
                card.setOrientation(LinearLayout.HORIZONTAL);
                card.setBackground(getDrawable(R.drawable.border));
                card.setPadding(30,50,30,50);

                //////////////////////////////////
                // create textview view
                // set properties
                // add to container above
                //////////////////////////////////
                TextView number = new TextView(getBaseContext());
                number.setText((i+1)+": ");
                number.setTextColor(getColor(R.color.fafaformBlace));
                number.setTextSize(18);
                card.addView(number);

                TextView name = new TextView(getBaseContext());
                name.setText(list_file.get(0));
                name.setTextColor(getColor(R.color.fafaformBlace));
                name.setTextSize(18);
                card.addView(name);

                //////////////////////////////////
                // add container view to existing view in layout file
                // layout property is apply here
                //////////////////////////////////
                view_main_layout.addView(card, layoutParams);

                //////////////////////////////////
                // most of views can be set on click
                // "card" is linearLayout
                // manually on click function is called here
                //////////////////////////////////
                card.setOnClickListener(new MyOwnOnClickListener(list_file.get(i)));
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    //////////////////////////////////
    // create OnClickListener class manually
    //////////////////////////////////
    private class MyOwnOnClickListener implements View.OnClickListener {

        String filename;

        //////////////////////////////////
        // normally java class
        //////////////////////////////////
        public MyOwnOnClickListener(String filename) {
            this.filename = filename;
        }

        @Override
        public void onClick(View v)
        {
            Intent detail_view = new Intent(getBaseContext(), DetailViewActivity.class);
            //////////////////////////////////
            // parameter past to next activity is declared below
            //////////////////////////////////
            detail_view.putExtra("filename",filename);
            startActivity(detail_view);
        }

    }
}
