import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;

import java.util.ArrayList;

public class Game extends PApplet {
    // TODO: declare game variables
    public ArrayList<Angels> angelList;
    int angelSpawntimer;
    Angels a;
    int x, y;
    int timer = 0;
    int x1, y1;
    String userInpt;
    PFont f;
    Eva e;
    int Score;
    PImage katniss;

    public void settings() {
        size(800, 800);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        angelList = new ArrayList<>();
        Angels brack = new Angels(0, 700, 400, 10);
        angelList.add(brack);
        angelSpawntimer = 70;
        userInpt = "";
        f = createFont("Arial", 12, true);
        e = new Eva(300, 300);
        Score = 0;
        katniss = loadImage("KatnissBestie.png");
    }

    /***
     * Draws each frame to the screen.  Runs automatically in a loop at frameRate frames a second.
     * tick each object (have it update itself), and draw each object
     */
    public void draw() {
        if(e.getLives() >= 0) {
            katniss.resize(50,300);
            textFont(f, 20);
            angelSpawntimer--;
            background(255);
            fill(0, 255, 0);
            e.draw(this);// paint screen white
            text("Score: " + Score, 20, 800);
            fill(0, 255, 0);          // load green paint color
            ellipse(mouseX, mouseY, 60, 60);  // draw circle at mouse loc
            if (angelSpawntimer <= 0) {
                for (int i = 0; i < 1; i++) {
                    x1 = (int) (Math.random() * 1000 + 800);
                    y1 = (int) (Math.random() * 750 + 50);
                    Angels a = new Angels(0, x1, y1, -2);
                    angelList.add(a);
                }
                angelSpawntimer = 70;
            }
            for (int i = 0; i < angelList.size(); i++) {
                Angels a = angelList.get(i);
                a.draw(this);
                textFont(f, 40);
                fill(255, 255, 255);
                text(a.wantedString, a.getX() - 15, a.getY());
                a.aimAt(300, 300);
                a.move(300);
                if (collisions(a, e)) {
                    a.dieAngelDie();
                    e.minusLives(1);
                    if (e.getLives() <= 0) {
                        e.dieEvaDie(this);
                    }
                    angelList.remove(a);
                    i--;
                }
                if (a.wantedString.equals(userInpt) && a.getX() <= 800) {
                    a.dieAngelDie();
                    angelList.remove(a);
                    Score++;
                    i--;
                    userInpt = "";
                }

            }
            System.out.println(userInpt);
            fill(255, 0, 0);
            text(e.getLives(), 30, 30);
        }else {
           background(0);
           fill(0,0,255);
           text("Game Over",250,300);
           String Sc = Score +"";
           if(timer == 0) {
               try {
                   showScores(Sc);
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
               try {
                   writeDataToFile("Scoreboard", Sc, true);
                   leaderboard(Sc, false);
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
           timer++;
        }

    }

    private void leaderboard(String sc, boolean b) throws IOException {
        String data = readFile("Scoreboard") ;
        String dataReal = "";
        ArrayList<Integer> arr = new ArrayList<>();
         String[] words = data.split("\n");
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            str= str.substring(7);
            str = str.trim();
            arr.add(Integer.parseInt(str));
        }
        Collections.sort(arr);

        for (int i = arr.size()-1; i > 0; i--) {
            dataReal+="Score: ";
            dataReal+=arr.get(i);
            dataReal+= "\n";

        }
        writeDataToFile("Leaderboard", dataReal, false);

    }

    private void showScores(String Sc) throws IOException {
        String data = readFile("Scoreboard") ;
        System.out.println(data);
    }


    public static void collision(ArrayList<Angels> angelList, Eva e) {
        for (int i = 0; i < angelList.size(); i++) {
            Angels a = angelList.get(i);
            if (collisions(a,e)) {
                a.dieAngelDie();
                e.minusLives(1);
            }
        }
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) {
                userInpt = "";
            }
        } else {
            userInpt += key;
        }
    }

    public static boolean collisions(Angels a, Eva e) {
        if (a.getX() >= e.getX() && a.getX() <= e.getX() + 100) {
            if (a.getY() >= e.getY() && a.getY() <= e.getY()+100) {
                System.out.println("they collided dududu");
                return true;
            }
        }
        return false;
    }

    public static void writeDataToFile(String filePath, String data, boolean appened) throws IOException {
        try (FileWriter f = new FileWriter(filePath, appened);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter writer = new PrintWriter(b);) {
            writer.println("Score: " + data);


        } catch (IOException error) {
            System.err.println("There was a problem writing to the file: " + filePath);
            error.printStackTrace();
        }
    }


    public static void main(String[] args) {
        PApplet.main("Game");
    }


    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
