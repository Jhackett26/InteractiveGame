import java.awt.*;

public class Player {
    public Image pic;
    public int x;
    public int y;
    public int dx=0;
    public int dy=0;
    public int width=100;
    public int height=100;
    public int health = 3;
    public Rectangle hitBox;

    public void move(int screenWidth, int screenHeight){
        x+=dx;
        y+=dy;
        hitBox = new Rectangle(x,y,width,height);
    }
}
