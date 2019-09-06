package com.example.testing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ContentFragment extends Fragment {

    Button resume;
    Button register;

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        // Inflate the layout for this fragment
        //////////////////////////////////
        // TODO link parameter to view in layout
        //////////////////////////////////
        resume = view.findViewById(R.id.view_info);
        register = view.findViewById(R.id.register);

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
                Intent res = new Intent(getContext(), ViewResumeActivity.class);
                startActivity(res);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(getContext(), RegisterActivity.class);
                startActivity(regis);
            }
        });

        return view;
    }
}
