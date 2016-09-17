package com.udacity.gradle.builditbigger.domain;

import android.os.AsyncTask;

import com.example.brianm.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by brianm on 21/08/2016.
 */
public class JokeManager {
  public static class EndpointsAsyncTask extends AsyncTask<String, Void, String> {

    private MyApi myApiService = null;

    @Override
    protected String doInBackground(String... params) {
      if (myApiService == null) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          .setRootUrl("https://builditbigger-141019.appspot.com/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
        myApiService = builder.build();
      }
      try {
        return myApiService.retrieveJoke().execute().getJoke();
      } catch (IOException e) {
        return e.getMessage();
      }
    }

    @Override
    protected void onPostExecute(String result) {
    }
  }

  public static String getResponse(){
    String response = "";
    try {
      response = new EndpointsAsyncTask().execute().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return response;
  }
}


