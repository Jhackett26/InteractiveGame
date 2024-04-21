import java.awt.*;

public class Sword {
    public Image pic;
    public int x;
    public int y;
    public int width=20;
    public int height=20;
    public boolean isAlive = true;
    public Rectangle hitBox;
    public Sword(){
        hitBox = new Rectangle(x,y,width,height);
    }

}
