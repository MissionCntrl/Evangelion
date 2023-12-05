import processing.core.PApplet;

public class Eva {
    public int lives;
    public boolean alive;
    public int x, y;
    private PApplet window;

    public Eva(int x, int y){
        this.alive = true;
        lives = 5;
        this.x = x;
        this.y = y;
    }
    public void draw(PApplet window){
        if(alive == true){
            window.fill(255,0,0);
            window.rect( x,y,100,100);
        }
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
