import processing.core.PApplet;
import processing.core.PFont;

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
    PFont f;
    Eva e;
    public void settings() {
        size(800, 800);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        angelList = new ArrayList<>();
        timer = 70;
        userInpt = "";
        f = createFont("Arial", 12, true);
        e = new Eva(400, 400);
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {

        textFont(f, 12);
        text("test", 20, 40);
        timer--;
        background(255);
        e.draw(this);// paint screen white
        fill(0,255,0);          // load green paint color
        ellipse(mouseX, mouseY, 60, 60);  // draw circle at mouse loc
        if(timer <= 0) {
            x1 = (int)(Math.random()*1000 + 800);
            y1 = (int)(Math.random()*750 + 50);
            Angels a = new Angels(0,x1,y1,-2);
            angelList.add(a);
            timer = 70;
        }
        for(int i = 0; i<angelList.size(); i++) {
            Angels a = angelList.get(i);
            a.draw(this);
            textFont(f, 40);
            fill(255,255,255);
            text(a.wantedString, a.x - 10, a.y);
            a.move(400);
            if(a.wantedString.equals(userInpt) && a.x <= 600){
                a.alive = false;
                angelList.remove(a);
                i--;
                userInpt = "";
            }
        }
        System.out.println(userInpt);


    }
    public void keyPressed(){
        if (key == CODED){
            if(keyCode == UP){
                userInpt = "";
            }
        } else {userInpt += key;}
    }

    public boolean collision (Angels a){
        if(a.x>=350){}
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
