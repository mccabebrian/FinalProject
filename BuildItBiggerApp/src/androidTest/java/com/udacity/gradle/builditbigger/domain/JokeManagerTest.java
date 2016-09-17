package com.udacity.gradle.builditbigger.domain;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

public class JokeManagerTest extends ApplicationTestCase{

  public JokeManagerTest() {
    super(Application.class);
  }

  @Test
  public void testIsNotNullResponse() throws InterruptedException {
    String test = JokeManager.getResponse();
    assertNotNull(test);
    assertTrue(!test.isEmpty());
  }

}
