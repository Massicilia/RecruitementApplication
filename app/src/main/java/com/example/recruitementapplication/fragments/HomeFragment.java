package com.example.recruitementapplication.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.recruitementapplication.R;


public class HomeFragment extends Fragment {

    ImageView ivCandidatesList;
    ImageView ivInterviewList;
    ImageView ivPlanInterview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivCandidatesList = getActivity().findViewById(R.id.ivCandidatList);
        ivInterviewList =  getActivity().findViewById(R.id.ivInterviewList);
        ivPlanInterview =  getActivity().findViewById(R.id.ivPlanInterview);

        ivCandidatesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new CandidatesListFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ivInterviewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new InterviewListFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });

        ivPlanInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new InterviewFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
    }

}
