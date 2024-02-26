import java.awt.*;

public class Bullet {
    public Image pic;
    public int x;
    public int y;
    public int dx;
    public int dy;
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
