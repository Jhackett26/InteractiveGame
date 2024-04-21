import java.awt.*;

public class Player {
    public Image pic;
    public int x;
    public int y;
    public int dx=6;
    public int dy=6;
    public int width=100;
    public int height=100;
    public int health = 5;
    public Rectangle hitBox;
    public boolean upIsPressed=false;
    public boolean downIsPressed=false;
    public boolean leftIsPressed=false;
    public boolean rightIsPressed=false;
    public Player(int paramX,int paramY){
        x = paramX;
        y = paramY;
        hitBox = new Rectangle(x+25,y,width/2,height);
    }
    public void move(int screenWidth, int screenHeight){
        if (upIsPressed){
            y-=dy;
        }
        if(downIsPressed){
            y+=dy;
        }
        if (leftIsPressed){
            x-=dx;
        }
        if(rightIsPressed){
            x+=dx;
        }
        if (y < 50){
            y=50;
        }
        if (y > 650-height){
            y=650-height;
        }
        if (x > 975-width){
            x = 975-width;
        }
        if (x < 25){
            x = 25;
        }
        hitBox = new Rectangle(x+25,y,width/2,height);
    }
}
