import java.awt.*;

public class Sword {
    public Image pic;
    public int x;
    public int y;
    public Rectangle hitBox;
    public boolean isActive = false;
    public Sword(){
        hitBox = new Rectangle(x,y,120,120);
    }

}
