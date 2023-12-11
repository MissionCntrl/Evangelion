import processing.core.PApplet;
import processing.core.PImage;

public class Eva {
    private int lives;
    private boolean alive;
    private int x, y;
    private PApplet window;
    private PImage katniss;
    public Eva(int x, int y){
        this.alive = true;
        lives = 5;
        this.x = x;
        this.y = y;
    }
    public void draw(PApplet window){
        if(alive){
            window.fill(255,0,0);
            katniss = window.loadImage("KatnissBestie.png");
            katniss.resize(50, 300);
        }
    }
    public void dieEvaDie(PApplet window){
        this.alive = false;
        gameOver(window);
    }

    public int getLives() {
        return lives;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void minusLives(int lives){
        this.lives -= lives;
        if(lives <= 0) {
            alive = false;
        }
    }

    public boolean isAlive(){
            if(!alive) {
            gameOver(window);
            return false;
        }
        return true;
    }
    public void gameOver(PApplet window){
        window.fill(0,0,0);
        window.rect(0,0,800,800);
        window.text("game over hehe",400,400);
    }
}
