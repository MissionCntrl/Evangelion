import processing.core.PApplet;

public class Angels {
    public boolean alive;
    public String displayString;
    private String wantedString;
    private int type; //if 0, then type the phrase, if 1, math problem, if 2, trivia problem!
    public int x,y,xSpeed;

    public Angels(int type, int x, int y, int xSpeed){
        this.type = type;
        if(type == 0) {
            wantedString = displayString;
        }
        double a  = Math.random();
        if(a > 0.5){
            wantedString = "0";
            displayString = "0";
        }else {
            wantedString = "1";
            displayString = "1";
        }
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
    }
    public void draw(PApplet window){
        window.fill(255,0,0);
        window.ellipse(x,y,50,50);
    }
    public void move() {
        x += xSpeed;
    }

    public void askQuestion(){
        System.out.println(displayString);
    }

    public void dieAngelDie(){
        this.alive = false;

        //le gasspppppp ohhh
    }
    public void amIDead(String userInput) {
        if(wantedString.equals(userInput)) {
                dieAngelDie();
            }
        }
    }

