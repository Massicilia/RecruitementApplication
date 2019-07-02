package com.example.recruitementapplication.request;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class InterviewCreation extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        String usernameColonPassword = "test-user:s3cur3d";
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
        Log.i("ETAPE", "BEFORE TRY");
        OutputStream out = null;
        try {
            URL serverUrl = new URL("https://api-recruitment.herokuapp.com/schedule");
            HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
            Log.i("ETAPE", "AFTER CONNECTION");
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Authorization", basicAuthPayload);
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            Log.i("ETAPE", "AFTER AUTHENTIFICATION");



            JSONObject jsonParam = new JSONObject();
            jsonParam.put("uuidCandidate", strings[0]);
            jsonParam.put("dateInterview", strings[1]);

            Log.i("JSON", jsonParam.toString());

            out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            Log.i("ETAPE", "CREATION OUTPUTSTREAM");
            writer.write(jsonParam.toString());
            Log.i("ETAPE", "WRITE DATAS");
            writer.flush();
            writer.close();
            Log.i("ETAPE", "CLOSE OBJECT");
            Log.i("STATUS", String.valueOf(urlConnection.getResponseCode()));
            Log.i("MSG" , urlConnection.getResponseMessage());


        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {

            if (out != null) {
                try {
                    out.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }
}

