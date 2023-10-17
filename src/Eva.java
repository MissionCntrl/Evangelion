import processing.core.PApplet;

public class Eva {
    public int lives;
    public boolean alive;
    public int x, y;
    private PApplet window;

    public Eva(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean isAlive(){
        if(!alive){
            gameOver(window);
            return false;
        }
        return true;
    }
    public void gameOver(PApplet window){
        window.fill(0,0,0);
        window.rect(0,0,800,800);
        window.text("game over hehe",300,300);
    }
}
