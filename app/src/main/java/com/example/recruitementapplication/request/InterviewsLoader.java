package com.example.recruitementapplication.request;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recruitementapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class InterviewsLoader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        String usernameColonPassword = "test-user:s3cur3d";
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
        Log.i("ETAPE", "BEFORE TRY");
        BufferedReader httpResponseReader = null;
        try {
            URL serverUrl = new URL("https://api-recruitment.herokuapp.com/interviews");
            HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
            Log.i("ETAPE", "CONNEXION ETABLIE");
            urlConnection.setRequestMethod("GET");

            urlConnection.addRequestProperty("Authorization", basicAuthPayload);

            InputStream in =
                    new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
            Log.i("ETAPE", "GET DATAS");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {

            if (httpResponseReader != null) {
                try {
                    httpResponseReader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    return null;
    }

    private static String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        Log.i("DATAS", String.valueOf(sb));
        is.close();
        return sb.toString();
    }

}
