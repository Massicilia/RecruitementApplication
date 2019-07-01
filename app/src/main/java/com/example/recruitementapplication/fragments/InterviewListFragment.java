package com.example.recruitementapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recruitementapplication.R;
import com.example.recruitementapplication.models.Interview;
import com.example.recruitementapplication.request.InterviewsLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class InterviewListFragment extends Fragment {

    ArrayList<String[]> interviews = new ArrayList<String[]>();

    private static final String[] TABLE_HEADERS = { " Candidate " , " Recruiter ", "Date" };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i("BEFORE 1", "FRAGMENT 1");
        try{
            Log.i("BEFORE", "FRAGMENT");
            String datas = new InterviewsLoader().execute().get();
            Log.i("DATA GET", datas);

            JSONArray reader = new JSONArray(datas);
            for(int i=0; i<reader.length(); i++){
                int id = reader.getJSONObject(i).getInt("idInterview");
                JSONObject JSONRecruiter = reader.getJSONObject(i).getJSONObject("recruiterFullDto");
                Log.i("ID Interview", id + "");
                Log.i("Recruiter LastName", JSONRecruiter.getString("lastName"));
                Log.i("Recruiter FirsName", JSONRecruiter.getString("firstName"));
                JSONObject JSONRCandidat = reader.getJSONObject(i).getJSONObject("candidateFullDto");
                Log.i("Candidat LastName", JSONRCandidat.getString("lastName"));
                Log.i("Candidat FirsName", JSONRCandidat.getString("firstName"));
                String localDateTime = reader.getJSONObject(i).getString("localDateTime");
                Log.i("Local Date Time", localDateTime);
                String[] interview={JSONRCandidat.getString("firstName")+ " "+ JSONRCandidat.getString("lastName"),
                        JSONRecruiter.getString("firstName") + " " + JSONRecruiter.getString("lastName"),
                         localDateTime };
                try{
                    interviews.add(interview);
                }catch(NullPointerException e){
                    e.printStackTrace();
                }

            }
        }catch(ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TableView<String[]> tableView = getActivity().findViewById(R.id.interviews);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), interviews));

        super.onViewCreated(view, savedInstanceState);
    }

}
