package com.needhamsoftware.edison.blink;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Created with IntelliJ IDEA.
 * User: gus
 * Date: 8/1/15
 */

/**
 * A super quick and dirty blink program for Intel Edison on mini-breakout board. 
 * This will oscillate the J19-6 pin, you can test this with your volt meter, 
 * or wire up a circuit that lights an LED on when it sees 1.8v and then attach 
 * it to J19-6 (signal) and J19-3(gnd)
 */

/*

These are the pins to see this work on the mini-breakout board 

looking at it from the bottom side, USB plugs to left, writing right side up
          
o o o o o o o o o o o o o o J17 
o o o o o o o o o o o o o o J18
o o o o o o o o S o o G o o J19
o o o o o o o o o o o o o o J20

*/

public class Blink {

  public static final String GPIO48 = "/sys/class/gpio/gpio48";

  public static void main(String[] args) throws InterruptedException {
    if (!(new File(GPIO48).exists())) {
      try {
        write("/sys/class/gpio/export", "48");
      } catch (IOException e) {
        System.out.println("Could not export GP48 (arduino IO7)");
        throw new RuntimeException(e);
      }
    }
    try {
      write(GPIO48+"/direction", "out");
    } catch (IOException e) {
      System.out.println("Could not set direction on GP48 (arduino IO7) to out");
      throw new RuntimeException(e);
    }
    boolean on = false;
    
    // change to while loop if you want to burn cpu cycles forever.
    for (int i = 0; i < 100; i++) {
      Thread.sleep(1000);
      try {
        write(GPIO48+"/value", on ? "0" : "1");
        on = !on;
      } catch (IOException e) {
        System.out.println("Could not write value to GP48 (arduino IO7");
      }
    }
  }


  private static void write(String filename, String value) throws IOException {
    Files.write(Paths.get(filename), value.getBytes());
  }

  private static String read(String filename) throws IOException {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }
}

