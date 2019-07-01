package com.example.recruitementapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.recruitementapplication.R;
import com.example.recruitementapplication.request.CandidatesLoader;
import com.example.recruitementapplication.request.InterviewsLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


public class CandidatesListFragment extends Fragment {

    ArrayList<String[]> candidates = new ArrayList<String[]>();
    private static final String[] TABLE_HEADERS = { "Name", "Mail", "Experience", "Enterprise", "Skills", "Keyskills" };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidates_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.i("BEFORE 1", "FRAGMENT 1");
        try{
            Log.i("BEFORE", "FRAGMENT");
            String datas = new CandidatesLoader().execute().get();
            Log.i("DATA GET", datas);

            JSONArray reader = new JSONArray(datas);
            for(int i=0; i<reader.length(); i++){
                String candidateName = reader.getJSONObject(i).getString("firstName") + " " + reader.getJSONObject(i).getString("lastName");
                String candidateMail = reader.getJSONObject(i).getString("mail");
                String candidateExperience = reader.getJSONObject(i).getInt("experience")+" ";
                String candidateEnterprise = reader.getJSONObject(i).getString("enterprise");
                Log.i("Name Candidate", candidateName );
                Log.i("candidateMail", candidateMail);
                Log.i("candidateExperience", candidateExperience + "");
                Log.i("candidateEnterprise", candidateEnterprise);
                JSONArray JSONRSkill = reader.getJSONObject(i).getJSONArray("skills");
                StringBuilder skills = new StringBuilder();
                for(int j=0; j<JSONRSkill.length(); j++){
                    String skill = JSONRSkill.getJSONObject(j).getString("nameSkill");
                    skills.append(skill);
                }
                Log.i("Skill Names", skills.toString());
                JSONArray JSONRKeySkill = reader.getJSONObject(i).getJSONArray("keySkills");
                StringBuilder keyskills = new StringBuilder();
                for(int j=0; j<JSONRSkill.length(); j++){
                    String keyskill = JSONRSkill.getJSONObject(j).getString("nameSkill");
                    keyskills.append(keyskill);
                }
                Log.i("KeySkill Names", keyskills.toString());

                String[] candidate={candidateName, candidateMail, candidateExperience, candidateEnterprise,
                        skills.toString(), keyskills.toString() };
                try{
                    candidates.add(candidate);
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
        TableView<String[]> tableView = getActivity().findViewById(R.id.candidates);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getContext(), TABLE_HEADERS));
        tableView.setDataAdapter(new SimpleTableDataAdapter(getContext(), candidates));

        super.onViewCreated(view, savedInstanceState);
    }

}
