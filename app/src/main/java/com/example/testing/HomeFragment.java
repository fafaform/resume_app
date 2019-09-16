package com.example.testing;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

//    Button resume;
//    Button register;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ////////////////////////////
        // TODO setting aminate view here
        ////////////////////////////
        ImageView images = view.findViewById(R.id.animateView);
        images.setBackgroundResource(R.drawable.animate);
        AnimationDrawable animate = (AnimationDrawable) images.getBackground();
        animate.start();
        ////////////////////////////
        // finish animate view
        ////////////////////////////
        return view;
    }


}
