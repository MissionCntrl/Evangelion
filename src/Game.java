import processing.core.PApplet;
import java.util.Scanner;

import java.util.ArrayList;

public class Game extends PApplet {
    // TODO: declare game variables
    public ArrayList<Angels> angelList;
    int timer;
    Angels a;
    int x,y;
    int x1, y1;
    String userInpt;
    public void settings() {
        size(800, 800);   // set the window size

    }

    public void setup() {
        // TODO: initialize game variables
        angelList = new ArrayList<>();
       String[] words = {"fa"};
        timer = 70;
        userInpt = "";

    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        timer--;
        background(255);    // paint screen white
        fill(0,255,0);          // load green paint color
        ellipse(mouseX, mouseY, 60, 60);  // draw circle at mouse loc
        if(timer <= 0) {
            x1 = (int)(Math.random()*1000 + 800);
            y1 = (int)(Math.random()*750 + 50);
            Angels a = new Angels(0,x1,y1,-2);
            angelList.add(a);
            timer = 70;
        }
        for(Angels a : angelList){
            a.draw(this);
            a.move();
            if(!a.alive){
                angelList.remove(a);
            }
        }


    }
    public void keyPressed(){
        userInpt += key;
        if (key == CODED){
            if(keyCode == ENTER){
                userInpt = "";
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
