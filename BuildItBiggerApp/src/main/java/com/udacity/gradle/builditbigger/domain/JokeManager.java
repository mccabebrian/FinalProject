package com.udacity.gradle.builditbigger.domain;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.brianm.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.R;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by brianm on 21/08/2016.
 */
public class JokeManager {
  public static class EndpointsAsyncTask extends AsyncTask<String, Void, String> {

    private MyApi myApiService = null;
    private Context mContext;
    ProgressDialog progress;

    public EndpointsAsyncTask(Context context) {
      mContext = context;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      progress = ProgressDialog.show(mContext, mContext.getString(R.string.loading_title),
        mContext.getString(R.string.loading_body), true);
    }

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
      progress.dismiss();
    }
  }

  public static String getResponse(Context context){
    String response = "";
    try {
      response = new EndpointsAsyncTask(context).execute().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return response;
  }
}


