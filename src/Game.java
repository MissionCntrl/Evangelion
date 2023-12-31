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
    public ArrayList<Peeta> PeetaList;
    int PeetaSpawntimer;
    Peeta a;
    int x, y;
    int timer = 0;
    int x1, y1;
    String userInpt;
    PFont f;
    Katniss e;
    int Score;
    PImage katniss;
    PImage fire;

    public void settings() {
        size(800, 800);   // set the window size
    }

    public void setup() {
        // TODO: initialize game variables
        PeetaList = new ArrayList<>();
        Peeta brack = new Peeta((int) (Math.random() * 2), 700, 400, 10, this);
        PeetaList.add(brack);
        PeetaSpawntimer = 20;
        userInpt = "";
        f = createFont("Arial", 12, true);
        e = new Katniss(300, 300);
        Score = 0;
        katniss = loadImage("KatnissBestie.png");
        fire = loadImage("fire.png");
        fire.resize(250, 600);
    }

    public void draw() {
        if (e.getLives() > 0) {
            background(0);
            //katniss.resize(50, 300);
            image(fire,0,0);
            textFont(f, 20);
            PeetaSpawntimer--;

            fill(0, 255, 0);
            e.draw(this);// paint screen white
            text("Score: " + Score, 20, 800);
            if (PeetaSpawntimer <= 0) {
                for (int i = 0; i < 3; i++) {
                    x1 = (int) (Math.random() * 1000 + 800);
                    y1 = (int) (Math.random() * 750 + 50);
                    Peeta a = new Peeta((int) (Math.random() * 2), x1, y1, -9, this);
                    PeetaList.add(a);
                }
                double spawnSetter = Score - 80;
                if(spawnSetter > 0 && PeetaSpawntimer > 30) {
                    PeetaSpawntimer -= spawnSetter;
                }else if(PeetaSpawntimer < 30) {
                    PeetaSpawntimer = 30;
                }else {
                    PeetaSpawntimer = 50;
                }

            }
            for (int i = 0; i < PeetaList.size(); i++) {
                Peeta a = PeetaList.get(i);
                a.draw(this);
                textFont(f, 40);
                fill(255, 255, 255);
                if (a.getType() == 0) {
                        text(a.getWantedString(), a.getX(), a.getY());
                        if (collisions(a, e)) {
                            a.diePeetaDie();
                            e.minusLives(1);
                            if (e.getLives() <= 0) {
                                e.dieEvaDie(this);
                            }
                            PeetaList.remove(a);
                            i--;
                        }
                        if (a.getWantedString().equals(userInpt) && a.getX() <= 800) {
                            a.diePeetaDie();
                            PeetaList.remove(a);
                            Score++;
                            i--;
                            userInpt = "";
                        }
                    }else if(a.getType() == 1) {
                        text("Hover Me", a.getX(), a.getY());
                    if (mouseX > a.getX() - 25 && mouseX < a.getX() + 25) {
                        if (mouseY > a.getY() - 25 && mouseY < a.getY() + 25) {
                            Score++;
                            a.diePeetaDie();
                            PeetaList.remove(a);
                            i--;
                        }
                    }
                    if(a.getX() <= 400) {
                        a.diePeetaDie();
                        e.minusLives(1);
                        if (e.getLives() <= 0) {
                            e.dieEvaDie(this);
                        }
                        PeetaList.remove(a);
                        i--;
                    }
                }
                a.aimAt(300, 300, Score);
                a.move(300);
            }
            System.out.println(userInpt);
            fill(255, 0, 0);
            text(e.getLives(), 30, 30);
        } else {
            background(0);
            fill(0, 0, 255);
            text("Game Over", 250, 300);
            text(Score, 250, 100);
            String Sc = Score + "";
            if (timer == 0) {
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


        private void leaderboard (String sc,boolean b) throws IOException {
            String data = readFile("Scoreboard");
            String dataReal = "";
            ArrayList<Integer> arr = new ArrayList<>();
            String[] words = data.split("\n");
            for (int i = 0; i < words.length; i++) {
                String str = words[i];
                str = str.substring(7);
                str = str.trim();
                arr.add(Integer.parseInt(str));
            }
            Collections.sort(arr);

            for (int i = arr.size() - 1; i > 0; i--) {
                dataReal += "Score: ";
                dataReal += arr.get(i);
                dataReal += "\n";

            }
            writeDataToFile("Leaderboard", dataReal, false);

        }

        private void showScores (String Sc) throws IOException {
            String data = readFile("Scoreboard");
        }

        public void keyPressed () {
            if (key == CODED) {
                if (keyCode == UP) {
                    userInpt = "";
                }
            } else {
                userInpt += key;
            }
        }

        public static boolean collisions (Peeta a, Katniss e){
            if (a.getX() - 25 < 400) {
                if (a.getY() >= e.getY() && a.getY() <= e.getY() + 100) {
                    return true;
                }
            }
            return false;
        }

        public static void writeDataToFile (String filePath, String data,boolean appened) throws IOException {
            try (FileWriter f = new FileWriter(filePath, appened);
                 BufferedWriter b = new BufferedWriter(f);
                 PrintWriter writer = new PrintWriter(b);) {
                writer.println("Score: " + data);


            } catch (IOException error) {
                System.err.println("There was a problem writing to the file: " + filePath);
                error.printStackTrace();
            }
        }


        public static void main (String[]args){
            PApplet.main("Game");
        }


        public static String readFile (String fileName) throws IOException {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        }
}
