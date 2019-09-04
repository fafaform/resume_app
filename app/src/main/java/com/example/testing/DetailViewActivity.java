package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DetailViewActivity extends AppCompatActivity {

    FileInputStream inputStream;
    ArrayList<String> resume_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        //////////////////////////////////
        // TODO bundle can be used on both putExtras and getExtras in case of wanna do
        //////////////////////////////////
        Bundle b = getIntent().getExtras();
        String filename = ""; // or other values
        if(b != null) {
            //////////////////////////////////
            // if string use bundle.getString
            // int use bundle.getInt
            // etc
            //////////////////////////////////
            filename = b.getString("filename");
        }

        resume_detail = new ArrayList<>();

        //////////////////////////////////
        // TODO this is local declaration
        //////////////////////////////////
        TextView firstname = findViewById(R.id.fname_detail);
        TextView lastname = findViewById(R.id.lname_detail);
        TextView bdate = findViewById(R.id.bdate_detail);
        TextView telephone = findViewById(R.id.tele_detail);

        //////////////////////////////////
        // TODO get all file list
        //////////////////////////////////
        try {
            inputStream = openFileInput(filename);

            //////////////////////////////////
            // TODO if not finished in a line, it is possible to enter to a next line
            //////////////////////////////////
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                resume_detail.add(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        for(int i=0; i<resume_detail.size(); i++){
            System.out.println(resume_detail.get(i));
        }

        //////////////////////////////////
        // TODO setText use to set text to every views that involve with text
        //////////////////////////////////
        firstname.setText(resume_detail.get(0).substring(resume_detail.get(0).indexOf(":") + 1));
        lastname.setText(resume_detail.get(1).substring(resume_detail.get(1).indexOf(":") + 1));
        bdate.setText(resume_detail.get(2).substring(resume_detail.get(2).indexOf(":") + 1));
        telephone.setText(resume_detail.get(3).substring(resume_detail.get(3).indexOf(":") + 1));
    }
}
