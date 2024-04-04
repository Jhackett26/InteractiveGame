//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// 1 KeyListener (Tab complete)
public class Main implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public boolean gamePlaying = false;
    public boolean gameOver = false;
    public boolean gamePaused = false;
    public Player player;
    public Wall[] walls;
    public BufferStrategy bufferStrategy;
    public Image background;
    public Enemy[] enemies;
    public int timer;

    /*** Arrays
     * Step 1: declare
     */
//public SoundFile ____;
    // Main method definition
    // This is the code that runs first and automatically
    public void pickSide(int ememiesNum) {

        int r = (int) (Math.random() * 4);
        if (r == 0) {
            enemies[ememiesNum] = new Enemy((int) (Math.random() * 601), 0);
        } else if (r == 1) {
            enemies[ememiesNum] = new Enemy((int) (Math.random() * 601), 900);
        } else if (r == 2) {
            enemies[ememiesNum] = new Enemy(0, (int) (Math.random() * 901));
        } else if (r == 3) {
            enemies[ememiesNum] = new Enemy(600, (int) (Math.random() * 901));
        }
        enemies[ememiesNum].pic = Toolkit.getDefaultToolkit().getImage("enemy.png");

    }
    public static void main(String[] args) {
        Main ex = new Main();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    // 2 add KeyListener to canvas (after graphics)
    public Main() { // BasicGameApp constructor
        setUpGraphics();
        canvas.addKeyListener(this);
        //variable and objects
        //create (construct) the objects needed for the game
        player = new Player(200, 300);
        player.pic = Toolkit.getDefaultToolkit().getImage("Player.png");
        background = Toolkit.getDefaultToolkit().getImage("background.png");
        walls = new Wall[4];
        for (int x = 0; x <= 1; x++) {
            walls[x] = new Wall(0, x * 650, 1000, 50);
            walls[x].pic = Toolkit.getDefaultToolkit().getImage("wall.jpeg");
            walls[x + 2] = new Wall(x * 950, 0, 50, 700);
            walls[x + 2].pic = Toolkit.getDefaultToolkit().getImage("wall.jpeg");
        }
        //___ = new SoundFile(filename);
        enemies = new Enemy[3];
        /***
         * Step 3: fill
         */

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        //game tics?
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
            timer++;
            if (timer>0){
                pickSide(0);
            }
            else if (timer>1000){
                pickSide(1);
            }
            else if (timer>2000){
                pickSide(2);
            }
        }
    }

    public void moveThings() {
        //call the move() code for each object
        player.move(700, 1000);
    }

    public void collisions() {
        //___.play();
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (gamePlaying==false) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Times Roman", Font.BOLD, 50));
            g.drawString("PRESS SPACE TO BEGIN", 200, 400);
        }//start screen
        else if (gamePlaying && !gameOver) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            //g.drawImage(oliverPic,500,0,300,300,null);
            if (player.health > 0) {
                g.drawImage(player.pic, player.x, player.y, player.width, player.height, null);
                //g.drawRect(player.hitBox.x, player.hitBox.y, player.hitBox.width, player.hitBox.height);
            }
            for (int x = 0; x <= 3; x++) {
                g.drawImage(walls[x].pic, walls[x].x, walls[x].y, walls[x].width, walls[x].height, null);
                g.drawRect(walls[x].hitBox.x, walls[x].hitBox.y, walls[x].hitBox.width, walls[x].hitBox.height);
            }

        }//game
        else if (gameOver) {

        }//win
        else if (gameOver) {

        }//lose
        g.dispose();
        bufferStrategy.show();
}


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 3 do this
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        if (keyCode == 87) {
            player.upIsPressed = true;
        }
        if (keyCode == 83) {
            player.downIsPressed = true;
        }
        if (keyCode == 65) {
            player.leftIsPressed = true;
        }
        if (keyCode == 68) {
            player.rightIsPressed = true;
        }
        if (!gamePlaying && keyCode == 32) {
            gamePlaying=true;
        }
    }
//        System.out.println("Key Pressed: "+key+", "+keyCode);
        // up = 38
        //down = 40
        //left = 37
        //right = 39


    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        if (keyCode == 87){
            player.upIsPressed = false;
        }
        if (keyCode == 83){
            player.downIsPressed=false;
        }
        if (keyCode == 65){
            player.leftIsPressed=false;
        }
        if (keyCode == 68){
            player.rightIsPressed=false;
        }
    }
}
