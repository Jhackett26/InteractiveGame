import java.awt.*;

public class Enemy {
    public Image pic;
    public int x;
    public int y;
    public int width=100;
    public int height=100;
    public boolean isAlive = true;
    public Rectangle hitBox;
    public int randomStart;
    public Enemy(int paramX,int paramY){
        x = paramX;
        y = paramY;
        hitBox = new Rectangle(x,y,width,height);
    }
    public void move(int PlayerxPos,int PlayeryPos){
        if(x < PlayerxPos){
            x+=2;
        }
        if(x > PlayerxPos){
            x-=2;
        }
        if(y < PlayeryPos){
            y+=2;
        }
        if(y > PlayeryPos){
            y-=2;
        }
//        for(int randNum=0;randNum<=3;randNum++){
//            if enemies[x].randomStart=
//        }
        hitBox = new Rectangle(x,y,width,height);
    }
}

//make sfx
//make enemies
//make bullets