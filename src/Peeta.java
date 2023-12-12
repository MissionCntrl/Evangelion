import processing.core.PApplet;
import processing.core.PImage;

public class Peeta {
    private boolean alive;
    private String displayString;
    private String wantedString;
    private int type; //if 0, then type the phrase, if 1, math problem, if 2, trivia problem!
    private int x, y, xSpeed, ySpeed;
    private PImage joshUgly;

    public Peeta(int type, int x, int y, int xSpeed, PApplet window) {
        this.alive = true;
        this.type = type;
        if (type == 0) {
            int a = (int) (Math.random() * 15);
            String s = a + "";
            wantedString = s;
            displayString = s;
            joshUgly = window.loadImage("uglyguy.png");
            joshUgly.resize(50,50);
        }else if(type == 1) {
            wantedString = null;
            displayString = "Click";
            joshUgly = window.loadImage("Petra.png");
            joshUgly.resize(50,50);
        }
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        if (y <= 400) {
            ySpeed = -ySpeed;
        }/**/

    }

    public int getxSpeed() {
        return xSpeed;
    }

    public String getWantedString() {
        return wantedString;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getType() {
        return type;
    }

    //?
    public void draw(PApplet window) {
        if(alive) {
            window.fill(255, 0, 0);
            window.image(joshUgly,x,y);
        }

    }

    public void aimAt(int tx, int ty, int Score) {
        int limit = 100;
        int factor = Score/5;
        factor = factor*-1;
        limit+=factor;
        xSpeed = (tx - x) / limit;
        ySpeed = (ty - y) / limit;

    }

    public void move(int moveTowards) {
        if (x <= 800) {
            x += xSpeed;
            y += ySpeed;
        } else {
            x += xSpeed;

        }
    }

    public void askQuestion() {
        System.out.println(displayString);
    }

    public void diePeetaDie() {
        this.alive = false;

        //le gasspppppp ohhh
    }

    public void amIDead(String userInput, int mouseX, int mouseY) {
        if (wantedString.equals(userInput) && type == 0) {
            diePeetaDie();
        }
    }

}

