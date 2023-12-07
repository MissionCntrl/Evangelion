import processing.core.PApplet;

public class Angels {
    public boolean alive;
    public String displayString;
    public String wantedString;
    private int type; //if 0, then type the phrase, if 1, math problem, if 2, trivia problem!
    public int x, y, xSpeed, ySpeed;

    public Angels(int type, int x, int y, int xSpeed) {
        this.alive = true;
        this.type = type;
        if (type == 0) {
            wantedString = displayString;
        }
        int a = (int) (Math.random() * 15);
        String s = a + "";
        wantedString = s;
        displayString = s;
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


    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    //?
    public void draw(PApplet window) {
        if(alive == true) {
            window.fill(255, 0, 0);
            window.ellipse(x, y, 50, 50);
        }

    }

    public void aimAt(int tx, int ty) {
        xSpeed = (tx - x) / 70;
        ySpeed = (ty - y) / 70;
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

    public void dieAngelDie() {
        this.alive = false;

        //le gasspppppp ohhh
    }

    public void amIDead(String userInput) {
        if (wantedString.equals(userInput)) {
            dieAngelDie();
        }
    }
}

