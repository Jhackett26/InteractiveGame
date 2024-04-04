import java.awt.*;

public class Bullet {
    public Image pic;
    public int x;
    public int y;
    public int dx;
    public int dy;
    public int width=20;
    public int height=20;
    public boolean isAlive = true;
    public Rectangle hitBox;
    public Bullet(int paramX,int paramY){
        x = paramX;
        y = paramY;
        hitBox = new Rectangle(x,y,width,height);
    }
    public void move(int screenWidth, int screenHeight){
        x+=dx;
        y+=dy;
        hitBox = new Rectangle(x,y,width,height);
    }
}
