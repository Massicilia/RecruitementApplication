package com.example.recruitementapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class InterviewListFragment extends Fragment {

    private static final String[] TABLE_HEADERS = { " Id", "Candidate", "Manager", "Date" };
    private static final String[][] DATA_TO_SHOW = { { "1", "Omar", "Sophie", "01/01/2020" },
            { "2", "Nom", "Nom2", "01/01/2020" } };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TableView<String[]> tableView = getActivity().findViewById(R.id.interviews);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), DATA_TO_SHOW));
            super.onViewCreated(view, savedInstanceState);
    }

}
