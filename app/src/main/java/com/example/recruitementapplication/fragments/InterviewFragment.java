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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recruitementapplication.R;
import com.example.recruitementapplication.request.CandidateByUUID;
import com.example.recruitementapplication.request.InterviewCreation;
import com.example.recruitementapplication.request.InterviewsLoader;

import java.util.concurrent.ExecutionException;


public class InterviewFragment extends Fragment {

    EditText uuidCandidate;
    EditText dateInterview;
    Button btnAddInterview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uuidCandidate= getActivity().findViewById(R.id.uuidCandidate);
        dateInterview = getActivity().findViewById(R.id.dateInterview);
        btnAddInterview = getActivity().findViewById(R.id.btnAddInterview);

        uuidCandidate.setText("123e4567-e89b-12d3-a456-556642449876"); //TO DELETE
        dateInterview.setText("2019-07-03T12:00:00"); //TO DELETE

        btnAddInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("uuid", uuidCandidate.getText().toString());
                Log.i("date", dateInterview.getText().toString());
                String uuid = uuidCandidate.getText().toString();
                String date = dateInterview.getText().toString();


                try {
                    // Log.i("BEFORE", "CALL CANDIDATEBYUUID");
                    // String candidate = new CandidateByUUID().execute("509837db-fb58-4ac1-a1a2-0b649d95bb62").get();
                    // Log.i("AFTER", "CALL CANDIDATEBYUUID");
                    Log.i("BEFORE", "CALL INTERVIEWCREATION");
                    String message = new InterviewCreation().execute(uuid, date).get();
                    showToast(message);
                    Log.i("AFTER", "CALL INTERVIEWCREATION");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public void showToast(final String Text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
