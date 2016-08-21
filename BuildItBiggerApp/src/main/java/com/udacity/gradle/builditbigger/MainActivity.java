package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.builditbigger.JokeTeller;
import com.udacity.gradle.builditbigger.domain.JokeManager;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

  private final String JOKE_KEY = "joke";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    try {
      Log.e("brian", new JokeManager.EndpointsAsyncTask().execute(new Pair<Context, String>(this, "brian")).get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }

  public void tellJoke(View view) {
    JokeTeller jokeTeller = new JokeTeller();
    Intent intent = new Intent(getApplication(), brianm.builditbiggerandroidlib.MainActivity.class);
    intent.putExtra(JOKE_KEY, jokeTeller.getJoke());
    startActivity(intent);
  }
}
