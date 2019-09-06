package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
    DatePickerDialog.OnDateSetListener date;
    Spinner title_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        bdate = findViewById(R.id.bdate);
        telephone = findViewById(R.id.tele);
        submit_btn = findViewById(R.id.submit_btn);
        title_spinner = findViewById(R.id.title_spinner);
        list_file = new ArrayList<>();

        //////////////////////////////////
        // TODO Calendar Initialize
        //////////////////////////////////
        bdate.setInputType(InputType.TYPE_NULL);
        final Calendar myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                bdate.setText(sdf.format(myCalendar.getTime()));
            }
        };
        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //////////////////////////////////
        // TODO setting dropdown list is showing here
        //////////////////////////////////
        // create a list of dropdown
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Mr");
        titles.add("Mrs");
        titles.add("Miss");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, titles);
        // setting the adapter to the view
        title_spinner.setAdapter(dataAdapter);
        // this use to set on select data form dropdown list
        title_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),title_spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //////////////////////////////////
        // TODO before getting some text remember to do some action or else the empty field will be return
        //////////////////////////////////
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////
                // TODO create file name to keep each people per file separately
                //////////////////////////////////
                filename = fname.getText().toString()+"_"+lname.getText().toString();

                //////////////////////////////////
                // TODO create contents that should put on a file
                //////////////////////////////////
                fileContents = "title:"+title_spinner.getSelectedItem().toString()+"\n"
                        +"fname:"+fname.getText().toString()+"\n"
                        +"lname:"+lname.getText().toString()+"\n"
                        +"bdate:"+bdate.getText().toString()+"\n"
                        +"telephone:"+telephone.getText().toString();

                //////////////////////////////////
                //TODO create file if not exist
                //////////////////////////////////
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

                //////////////////////////////////
                //TODO get all file list
                //////////////////////////////////
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

                //////////////////////////////////
                //TODO write file
                //////////////////////////////////
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();

                    //////////////////////////////////
                    // TODO prevent duplicate saving file
                    //////////////////////////////////
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
                //////////////////////////////////
                // TODO "finish" is used to finish an activity
                //////////////////////////////////
                finish();
            }
        });
    }
}
