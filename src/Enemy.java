import java.awt.*;

public class Enemy {
    public Image pic;
    public int x;
    public int y;
    public int dx=(int)(Math.random()*2+1);
    public int dy=(int)(Math.random()*2+1);
    public int width=100;
    public int height=100;
    public boolean isAlive = true;
    public Rectangle hitBox;

    public void move(int screenWidth, int screenHeight){
        x+=dx;
        y+=dy;
        hitBox = new Rectangle(x,y,width,height);
    }
}
