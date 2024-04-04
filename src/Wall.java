import java.awt.*;

public class Wall {
    public Image pic;
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean isAlive = true;
    public Rectangle hitBox;
    public Wall(int paramX,int paramY,int paramWidth,int paramHeight){
        x = paramX;
        y = paramY;
        width = paramWidth;
        height = paramHeight;
        hitBox = new Rectangle(x,y,width,height);
    }
}
