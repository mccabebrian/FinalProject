package com.builditbigger;

import java.util.ArrayList;
import java.util.Random;

public class JokeTeller {
  ArrayList<String> ar = new ArrayList<String>();

  public String getJoke(){
    return "tdttdtdt";
  }

  public String pickRandomJoke(){
    ar.add("Can a kangaroo jump higher than a house? \n Of course, a house doesn’t jump at all.");
    ar.add("My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.");
    ar.add("What is the difference between a snowman and a snowwoman? \n Snowballs.");

    int rnd = new Random().nextInt(ar.size());
    return ar.get(rnd);
  }
}
