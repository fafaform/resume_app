package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    FileOutputStream outputStream;
    FileInputStream inputStream;
    EditText fname;
    EditText lname;
    EditText bdate;
    EditText telephone;
    Button submit_btn;
    String filename;
    String fileContents;
    ArrayList<String> list_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        bdate = findViewById(R.id.bdate);
        telephone = findViewById(R.id.tele);
        submit_btn = findViewById(R.id.submit_btn);
        list_file = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //////////////////////////////////
        // before getting some text remember to do some action or else the empty field will be return
        //////////////////////////////////
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filename = fname.getText().toString()+"_"+lname.getText().toString();

                fileContents = "fname:"+fname.getText().toString()+"\n"+"lname:"+lname.getText().toString()+"\n"+"bdate:"+bdate.getText().toString()+"\n"
                        +"telephone:"+telephone.getText().toString();

                //TODO create file if not exist
                try {
                    inputStream = openFileInput(Global.resume_list_file);
                } catch (FileNotFoundException fe){
                    try {
                        File file = new File(getApplicationContext().getFilesDir(), Global.resume_list_file);
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                //TODO get all file list
                try {
                    inputStream = openFileInput(Global.resume_list_file);

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            inputStream, StandardCharsets.UTF_8));

                    String line;

                    while ((line = br.readLine()) != null) {
                        list_file.add(line);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

                //TODO write file
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();

                    boolean already_have = false;
                    for(int i = 0; i < list_file.size(); i++){
                        if(list_file.get(i).equals(filename)){
                            already_have = true;
                            break;
                        }
                    }
                    if(!already_have) {
                        outputStream = openFileOutput(Global.resume_list_file, Context.MODE_APPEND);
                        outputStream.write((filename + "\n").getBytes());
                        outputStream.close();
                        Toast.makeText(getBaseContext(),"Create New Resume",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getBaseContext(),"Update Existing Resume",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
