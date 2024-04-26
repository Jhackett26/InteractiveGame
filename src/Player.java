import java.awt.*;

public class Player {
    public Image pic;
    public int x;
    public int y;
    public int health = 3;
    public Rectangle hitBox;
    public boolean wIsPressed =false;
    public boolean sIsPressed =false;
    public boolean aIsPressed =false;
    public boolean dIsPressed =false;
    public boolean upIsPressed =false;
    public boolean downIsPressed =false;
    public boolean leftIsPressed =false;
    public boolean rightIsPressed =false;
    public boolean iFrames;
    public int timeWhenHit;
    public Player(int paramX,int paramY){
        x = paramX;
        y = paramY;
        hitBox = new Rectangle(x+25,y,50,100);
    }
    public void move(int screenWidth, int screenHeight){
        if (wIsPressed){
            y-=7;
        }
        if(sIsPressed){
            y+=7;
        }
        if (aIsPressed){
            x-=7;
        }
        if(dIsPressed){
            x+=7;
        }
        if (y < 50){
            y=50;
        }
        if (y > 550){
            y=550;
        }
        if (x > 875){
            x = 875;
        }
        if (x < 25){
            x = 25;
        }
        hitBox = new Rectangle(x+25,y,50,100);
    }
}
