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
    boolean repeat1 =true;
    boolean repeat2 =true;
    boolean repeat3 =true;
    int deadEnemies = 0;
    int timeWhenAttack;

    public SoundFile grunt;
    public SoundFile attack;
    public SoundFile enemyDead;
    public Sword uSword;
    public Sword dSword;
    public Sword lSword;
    public Sword rSword;
    public String directionAttacked;


    /*** Arrays
     * Step 1: declare
     */
//public SoundFile ____;
    // Main method definition
    // This is the code that runs first and automatically

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
        for (int i=0;i<3;i++) {
            enemies[i] = new Enemy(-100, -100);
            enemies[i].pic = Toolkit.getDefaultToolkit().getImage("enemy.png");
        }
        grunt =new SoundFile("grunt.wav");
        attack = new SoundFile("Sword Whip 03.wav");
        enemyDead = new SoundFile("Tape Start.wav");
        uSword = new Sword();
        uSword.pic = Toolkit.getDefaultToolkit().getImage("attackU.png");
        dSword = new Sword();
        dSword.pic = Toolkit.getDefaultToolkit().getImage("attackD.png");
        lSword = new Sword();
        lSword.pic = Toolkit.getDefaultToolkit().getImage("attackL.png");
        rSword = new Sword();
        rSword.pic = Toolkit.getDefaultToolkit().getImage("attackR.png");
        uSword.hitBox = new Rectangle(player.x,player.y-120,120,120);
        dSword.hitBox = new Rectangle(player.x,player.y+100,120,120);
        lSword.hitBox = new Rectangle(player.x-120,player.y,120,120);
        rSword.hitBox = new Rectangle(player.x+100,player.y,120,120);


    } // end BasicGameApp constructor

    public void pickSides(int i){
        int r = (int)(Math.random()*4);
            if (r == 0) {
                enemies[i].x = ((int) (Math.random() * 601));
                enemies[i].y = 0;
            } else if (r == 1) {
                enemies[i].x = ((int) (Math.random() * 601));
                enemies[i].y = 900;
            } else if (r == 2) {
                enemies[i].x = 0;
                enemies[i].y = ((int) (Math.random() * 901));
            } else if (r == 3) {
                enemies[i].x = 600;
                enemies[i].y = ((int) (Math.random() * 901));
            }
        }


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        //game tics
        while (true) {
            if (gamePlaying) {
                moveThings();  //move all the game objects
                collisions();
                timer++;
                uSword.hitBox.x=player.x;
                uSword.hitBox.y=player.y-120;
                dSword.hitBox.x=player.x;
                dSword.hitBox.y=player.y+100;
                lSword.hitBox.x=player.x-120;
                lSword.hitBox.y=player.y;
                rSword.hitBox.x=player.x+100;
                rSword.hitBox.y=player.y;

                if ((timer > 2000 && repeat3) || (deadEnemies==2 && repeat3)) {
                    pickSides(2);
                    repeat3 = false;
                }
                else if ((timer > 1000 && repeat2) || (deadEnemies==1 && repeat2)) {
                    pickSides(1);
                    repeat2 = false;
                }
                else if (repeat1) {
                    pickSides(0);
                    repeat1 = false;
                }

            }
            render();  // paint the graphics
            pause(10); // sleep for 10 ms

        }
    }

    public void moveThings() {
        //call the move() code for each object
        player.move(700, 1000);
        if (!repeat1){
            enemies[0].move(player.x, player.y);
        }
        if (!repeat2){
            enemies[1].move(player.x, player.y);
        }
        if (!repeat3){
            enemies[2].move(player.x, player.y);
        }
    }

    public void collisions(){
        if (!gameOver){
            for (int i = 0; i < 3; i++) {
                if (player.hitBox.intersects(enemies[i].hitBox) && !player.iFrames && enemies[i].health>0) {
                    player.timeWhenHit = timer;
                    player.health -= 1;
                    player.iFrames = true;
                    grunt.play();
                }
                if ((timer - player.timeWhenHit) > 50) {
                    player.iFrames = false;
                }
                if (uSword.hitBox.intersects(enemies[i].hitBox) && !enemies[i].iFrames && uSword.isActive) {
                    enemies[i].timeWhenHit = timer;
                    enemies[i].health -= 1;
                    System.out.println("enemy: "+i+", "+enemies[i].health);
                    enemies[i].iFrames = true;
                }
                if (dSword.hitBox.intersects(enemies[i].hitBox) && !enemies[i].iFrames && dSword.isActive) {
                    enemies[i].timeWhenHit = timer;
                    enemies[i].health -= 1;
                    System.out.println("enemy: "+i+", "+enemies[i].health);
                    enemies[i].iFrames = true;
                }
                if (rSword.hitBox.intersects(enemies[i].hitBox) && !enemies[i].iFrames && rSword.isActive) {
                    enemies[i].timeWhenHit = timer;
                    enemies[i].health -= 1;
                    System.out.println("enemy: "+i+", "+enemies[i].health);
                    enemies[i].iFrames = true;
                }
                if (lSword.hitBox.intersects(enemies[i].hitBox) && !enemies[i].iFrames && lSword.isActive) {
                    enemies[i].timeWhenHit = timer;
                    enemies[i].health -= 1;
                    System.out.println("enemy: "+i+", "+enemies[i].health);
                    enemies[i].iFrames = true;
                }
                if ((timer - enemies[i].timeWhenHit) > 20) {
                    enemies[i].iFrames = false;
                }
            }
        }

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        if (player.health<1){
            gameOver=true;
        }

        if (enemies[0].health<1 && enemies[1].health<1 && enemies[2].health<1){
            gameOver=true;
        }
        if (!gamePlaying) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Times Roman", Font.BOLD, 50));

            g.drawString(" WASD TO MOVE", 260, 250);
            g.drawString(" ARROW KEYS TO ATTACK",160 , 325);
            g.drawString(" PRESS SPACE TO BEGIN", 190, 400);
        }//start screen
        else if (gamePlaying && !gameOver) {
            g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
            //g.drawImage(oliverPic,500,0,300,300,null);
            if (player.health > 0) {
                g.drawImage(player.pic, player.x, player.y, player.width, player.height, null);
            }
            for (int x = 0; x <= 3; x++) {
                g.drawImage(walls[x].pic, walls[x].x, walls[x].y, walls[x].width, walls[x].height, null);
            }
            g.setColor(Color.red);
            g.setFont(new Font("Times Roman", Font.BOLD, 25));
            g.drawString("HEALTH: "+player.health, 410, 80);
            for (int i = 0; i < 3; i++) {
                if(enemies[i].health>0){
                g.drawImage(enemies[i].pic, enemies[i].x, enemies[i].y, enemies[i].width, enemies[i].height, null);
            }
                if(enemies[i].health ==0){
                    deadEnemies+=1;
                    enemyDead.play();
                    enemies[i].health=-1;
                }

            }

            //Render Sword



            if(timer-timeWhenAttack > 15) {
                if (player.upIsPressed) {
                    g.drawImage(uSword.pic, player.x, player.y - 100, 100, 100, null);
                    timeWhenAttack = timer;
                    directionAttacked="up";
                    attack.play();
                    uSword.isActive=true;
                }
                else if (player.downIsPressed) {
                    g.drawImage(dSword.pic, player.x, player.y + 100, 100, 100, null);
                    timeWhenAttack = timer;
                    directionAttacked = "down";
                    attack.play();
                    dSword.isActive=true;
                }
                else if (player.leftIsPressed) {
                    g.drawImage(lSword.pic, player.x - 100, player.y, 100, 100, null);
                    timeWhenAttack = timer;
                    directionAttacked = "left";
                    attack.play();
                    lSword.isActive=true;
                }
                else if (player.rightIsPressed) {
                    g.drawImage(rSword.pic, player.x + 100, player.y, 100, 100, null);
                    timeWhenAttack = timer;
                    directionAttacked = "right";
                    attack.play();
                    rSword.isActive=true;
                }
                else{
                    uSword.isActive=false;
                    dSword.isActive=false;
                    lSword.isActive=false;
                    rSword.isActive=false;
                }
            }
            else{
                if(directionAttacked == "up"){
                    g.drawImage(uSword.pic, player.x, player.y-120, 120, 120, null);
                }
                else if(directionAttacked == "down"){
                    g.drawImage(dSword.pic, player.x, player.y + 100, 120, 120, null);
                }
                else if(directionAttacked == "left"){
                    g.drawImage(lSword.pic, player.x - 120, player.y, 120, 120, null);
                }
                else if(directionAttacked == "right"){
                    g.drawImage(rSword.pic, player.x + 100, player.y, 120, 120, null);
                }
                else{
                    uSword.isActive=false;
                    dSword.isActive=false;
                    lSword.isActive=false;
                    rSword.isActive=false;
                }
            }
            //hitboxes
            g.drawRect(rSword.hitBox.x, rSword.hitBox.y, rSword.hitBox.width, rSword.hitBox.height);
            g.drawRect(lSword.hitBox.x, lSword.hitBox.y, lSword.hitBox.width, lSword.hitBox.height);
            g.drawRect(dSword.hitBox.x, dSword.hitBox.y, dSword.hitBox.width, dSword.hitBox.height);
            g.drawRect(uSword.hitBox.x, uSword.hitBox.y, uSword.hitBox.width, uSword.hitBox.height);
            g.drawRect(player.hitBox.x, player.hitBox.y, player.hitBox.width, player.hitBox.height);
            for(int i=0;i<3;i++){
                g.drawRect(enemies[i].hitBox.x, enemies[i].hitBox.y, enemies[i].hitBox.width, enemies[i].hitBox.height);
            }



        }//game
        else if (gameOver && player.health>0) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Times Roman", Font.BOLD, 50));
            g.drawString("YOU WIN!!!", 355, 350);
        }//win
        else if (gameOver && player.health<1) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.white);
            g.setFont(new Font("Times Roman", Font.BOLD, 50));
            g.drawString("YOU LOSE", 360, 350);
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
        int keyCode = e.getKeyCode();
        if (keyCode == 87) {
            player.wIsPressed = true;
        }
        if (keyCode == 83) {
            player.sIsPressed = true;
        }
        if (keyCode == 65) {
            player.aIsPressed = true;
        }
        if (keyCode == 68) {
            player.dIsPressed = true;
        }
        if (keyCode == 38) {
            player.upIsPressed = true;
        }
        if (keyCode == 40) {
            player.downIsPressed = true;
        }
        if (keyCode == 37) {
            player.leftIsPressed = true;
        }
        if (keyCode == 39) {
            player.rightIsPressed = true;
        }

        if (!gamePlaying && keyCode == 32) {
            gamePlaying=true;
        }
//        System.out.println("Key Pressed: "+key+", "+keyCode);
    }
        // up = 38
        //down = 40
        //left = 37
        //right = 39


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 87){
            player.wIsPressed = false;
        }
        if (keyCode == 83){
            player.sIsPressed =false;
        }
        if (keyCode == 65){
            player.aIsPressed =false;
        }
        if (keyCode == 68){
            player.dIsPressed =false;
        }
        if (keyCode == 38) {
            player.upIsPressed = false;
        }
        if (keyCode == 40) {
            player.downIsPressed = false;
        }
        if (keyCode == 37) {
            player.leftIsPressed = false;
        }
        if (keyCode == 39) {
            player.rightIsPressed = false;
        }
    }
}
