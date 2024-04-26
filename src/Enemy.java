import java.awt.*;

public class Enemy {
    public Image pic;
    public int x;
    public int y;
    public int width=100;
    public int height=100;
    public Rectangle hitBox;
    public int health = 10;
    public boolean iFrames;
    public int timeWhenHit;
    public Enemy(int paramX,int paramY){
        x = paramX;
        y = paramY;
        hitBox = new Rectangle(x+25,y,75,height);
    }
    public void move(int PlayerxPos,int PlayeryPos){
        if(x < PlayerxPos){
            x+=3;
        }
        if(x > PlayerxPos){
            x-=3;
        }
        if(y < PlayeryPos){
            y+=3;
        }
        if(y > PlayeryPos){
            y-=3;
        }
//        for(int randNum=0;randNum<=3;randNum++){
//            if enemies[x].randomStart=
//        }
        hitBox = new Rectangle(x+25,y,75,height);
    }
}

//make sfx
//make enemies
//make bullets