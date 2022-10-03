package com.dailyreport.five;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dailyreport.MainActivity;
import com.dailyreport.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FiveFragment extends Fragment {
    public ViewGroup rootview;
    public FirebaseDatabase database;
    public DatabaseReference myRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_shap_five_intro, container, false);
        if (savedInstanceState != null) { }
        initSetting();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("permission_phone");
        return rootview;
    }

    private void initSetting() {

        //((DatabaseReference) myRef).setValue("Hello, World!");


        MainActivity mainActivity = (MainActivity) getActivity();
        //mainActivity.changeFragment("DbMonitorFragment");
        Runnable runnable = () -> mainActivity.changeFragment("DbMonitorFragment");
        Handler mHandler = new Handler();
        mHandler.postDelayed(runnable, 2500);
    }

    public DatabaseReference getMyRef() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d("YYYM", "Value is: " + value.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("YYYM", "Failed to read value.", databaseError.toException());
            }
        });
        return myRef;
    }
}
