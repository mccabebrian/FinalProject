package com.udacity.gradle.builditbigger.domain;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.example.brianm.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by brianm on 21/08/2016.
 */
public class JokeManager {
  public static class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
      if (myApiService == null) {  // Only do this once
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 10.0.2.2 is localhost's IP address in Android emulator
          // - turn off compression when running against local devappserver
          .setRootUrl("https://builditbigger-141019.appspot.com/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
        // end options for devappserver

        myApiService = builder.build();
      }

      context = params[0].first;

      try {
        return myApiService.retrieveJoke().execute().getJoke();
      } catch (IOException e) {
        return e.getMessage();
      }
    }

    @Override
    protected void onPostExecute(String result) {
      Toast.makeText(context, result, Toast.LENGTH_LONG).show();
      Log.e("brian", result);
    }
  }

}
