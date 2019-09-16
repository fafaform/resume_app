package com.example.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.testing.Global.CAMERA_PERMISSION;

public class EtcFragment extends Fragment {

    private static final int CAMERA_PIC_REQUEST = 123;
    View view;
    ImageView myPhoto;
    public EtcFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_etc, container, false);

        ////////////////////////////
        // TODO request permission on JAVA
        // this need to grant permission on manifest too
        // CAMERA_PERMISSION is on Global.java
        ////////////////////////////
        String[] PERMISSIONS = {CAMERA_PERMISSION};
        if (!hasPermissions(view.getContext(), PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) view.getContext(), PERMISSIONS, 1);
        } else {
            // Open your camera here.
        }

        myPhoto = view.findViewById(R.id.myPhoto);
        Button camera = view.findViewById(R.id.takePhotoBtn);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////////////////////////////
                // TODO Open camera uaing Intent
                ////////////////////////////
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, CAMERA_PIC_REQUEST);
            }
        });

        return view;
    }

    ////////////////////////////
    // result function after taking a photo
    ////////////////////////////
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            myPhoto.setImageBitmap(photo);
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    ////////////////////////////
    // checking permission
    ////////////////////////////
    private boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
