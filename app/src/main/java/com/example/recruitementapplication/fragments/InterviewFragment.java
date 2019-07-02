package com.example.recruitementapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recruitementapplication.R;
import com.example.recruitementapplication.request.CandidateByUUID;
import com.example.recruitementapplication.request.InterviewCreation;
import com.example.recruitementapplication.request.InterviewsLoader;

import java.util.concurrent.ExecutionException;


public class InterviewFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try {
           // Log.i("BEFORE", "CALL CANDIDATEBYUUID");
           // String candidate = new CandidateByUUID().execute("509837db-fb58-4ac1-a1a2-0b649d95bb62").get();
           // Log.i("AFTER", "CALL CANDIDATEBYUUID");
            Log.i("BEFORE", "CALL INTERVIEWCREATION");
            new InterviewCreation().execute("509837db-fb58-4ac1-a1a2-0b649d95bb62", "2019-07-03T12:00:00").get();
            Log.i("AFTER", "CALL INTERVIEWCREATION");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
