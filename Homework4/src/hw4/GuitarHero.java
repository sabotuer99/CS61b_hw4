package hw4;

import synthesizer.GuitarString;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

// A client that uses the synthesizer package to replicate a plucked guitar string sound
public class GuitarHero {
      public static void main(String[] args) {

          // create two guitar strings, for concert A and C
/*          double CONCERT_A = 440.0;
          double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0); 
          synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
          synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);*/
    	  String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    	  synthesizer.GuitarString[] strings = new synthesizer.GuitarString[37];
    	  for(int i = 0; i < 37; i += 1){
    		  double freq = 440 * Math.pow(2, (i - 24.0)/ 12.0);
    		  //System.out.println(44100/freq);
    		  strings[i] = new GuitarString(freq);
    	  }
    	  
          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  int index = keyboard.indexOf(key);
                  if(index != -1){
                	  strings[index].pluck();
                  }
                  /*if      (key == 'a') { stringA.pluck(); }
                  else if (key == 'c') { stringC.pluck(); }*/
              }

              // compute the superposition of samples
              double sample = 0;
              for(GuitarString string: strings){
            	  sample += string.sample();
              }
  
              // play the sample on standard audio
              // note: this is just playing the double value YOUR GuitarString
              //       class is generating. 
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
/*              stringA.tic();
              stringC.tic();*/
              for(GuitarString string: strings){
            	  string.tic();
              }
          }
       }
  }

