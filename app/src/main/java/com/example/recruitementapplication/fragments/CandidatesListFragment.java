package com.example.recruitementapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class CandidatesListFragment extends Fragment {

    private static final String[] TABLE_HEADERS = { "First name", "Last Name", "age", "Number" };
    private static final String[][] DATA_TO_SHOW = { { "Manuel", "THE WEEKND", "100", "0000000000" },
            { "Massi", "CILIA", "50", "000000001" } };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidates_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TableView<String[]> tableView = getActivity().findViewById(R.id.candidates);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), DATA_TO_SHOW));
            super.onViewCreated(view, savedInstanceState);
    }

}
