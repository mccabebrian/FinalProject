package brianm.builditbiggerandroidlib;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  private final String JOKE_KEY = "joke";
  private String joke;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_joke);

    Intent intent = getIntent();
    joke = intent.getStringExtra(JOKE_KEY);

    TextView textView = (TextView) findViewById(R.id.joke_text);
    textView.setText(joke);
  }
}
