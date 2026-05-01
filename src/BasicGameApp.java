//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

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
   
	public BufferStrategy bufferStrategy;
	public Image MarioPic;
    public Image mushroomPic;
    public Image backgroundPic;
    public Image gameOver;

    public Image turtlePic;
    public Image squishedturtlePic;
    public Image start;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
    private Mario Mario1;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipeteleport;
    private Pipe pipeteleport2;
    private bricks brick1;
    private bricks brick2;
    private bricks brick3;
    private bricks brick4;
    private bricks brick5;
    private bricks brick6;
    private bricks brick7;
    private Mushroom mushroom1;
    private Mushroom goodMushroom;
    private Turtle turtle1;
    private startScreen pressStart;

    public coins[] coin;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();


      //variable and objects
      //create (construct) the objects needed for the game and load up 
        backgroundPic = Toolkit.getDefaultToolkit().getImage("background.png");
        start = Toolkit.getDefaultToolkit().getImage("start.png");
        gameOver = Toolkit.getDefaultToolkit().getImage("gameOver.png");


        pressStart = new startScreen();
        //the start screen that disappears when the mouse enters
        Mario1 = new Mario(10,100);
        //the main character
        mushroom1 = new Mushroom(200,560);
        //mushroom that shrinks mario
        goodMushroom = new Mushroom(405,360);
        goodMushroom.width = 50;
        goodMushroom.height = 50;
        goodMushroom.hitbox = new Rectangle(goodMushroom.xpos, goodMushroom.ypos, goodMushroom.width, goodMushroom.height);
        //mushroom that makes you grow big
        turtle1 = new Turtle(500, 550);
        //the turtle that goes in its shell when you hit it
        pipe1 = new Pipe(170, 550);
        pipe1.height = 200;
        pipe1.hitbox = new Rectangle(pipe1.xpos, pipe1.ypos, pipe1.width, pipe1.height);
        //the first pipe which if you hit from the side, you will bounce off
        pipe2 = new Pipe(570, 448);
        pipe2.height = 400;
        pipe2.hitbox = new Rectangle(pipe2.xpos, pipe2.ypos, pipe2.width, pipe2.height);
        //same as the first but for the longer one

        brick1 = new bricks(160,260);
        brick1.height = 40;
        brick1.width = 150;
        brick1.hitbox = new Rectangle(brick1.xpos, brick1.ypos, brick1.width, brick1.height);
        //this along with the following bricks are the the bricks floating in air which mario can stand on

        brick2 = new bricks(325,410);
        brick2.height = 40;
        brick2.width = 185;
        brick2.hitbox = new Rectangle(brick2.xpos, brick2.ypos, brick2.width, brick2.height);

        brick3 = new bricks(685,410);
        brick3.height = 40;
        brick3.width = 40;
        brick3.hitbox = new Rectangle(brick3.xpos, brick3.ypos, brick3.width, brick3.height);

        brick4 = new bricks(925,410);
        brick4.height = 40;
        brick4.width = 35;
        brick4.hitbox = new Rectangle(brick4.xpos, brick4.ypos, brick4.width, brick4.height);

        brick5 = new bricks(685,175);
        brick5.height = 40;
        brick5.width = 120;
        brick5.hitbox = new Rectangle(brick5.xpos, brick5.ypos, brick5.width, brick5.height);

        brick6 = new bricks(805,410);
        brick6.height = 40;
        brick6.width = 35;
        brick6.hitbox = new Rectangle(brick6.xpos, brick6.ypos, brick6.width, brick6.height);

        brick7 = new bricks(805,260);
        brick7.height = 5;
        brick7.width = 40;
        brick7.hitbox = new Rectangle(brick7.xpos, brick7.ypos, brick7.width, brick7.height);




        pipeteleport = new Pipe(170, 510);
        pipeteleport.height = 40;
        pipeteleport.hitbox = new Rectangle(pipeteleport.xpos, pipeteleport.ypos, pipeteleport.width, pipeteleport.height);
        //this along with the next one defide the hitboxed of the top of the pipes which can be used to teleport to the other pipe.

        pipeteleport2 = new Pipe(570, 408);
        pipeteleport2.height = 50;
        pipeteleport2.hitbox = new Rectangle(pipeteleport2.xpos, pipeteleport2.ypos, pipeteleport2.width, pipeteleport2.height);

        MarioPic = Toolkit.getDefaultToolkit().getImage("mario.png"); //load the picture
        mushroomPic = Toolkit.getDefaultToolkit().getImage("mushroom.png");
        turtlePic = Toolkit.getDefaultToolkit().getImage("Turtle.png");//load the picture
        squishedturtlePic = Toolkit.getDefaultToolkit().getImage("squished turtle.png");//load the picture
        //this sets the image of the characters

        coin = new coins[8];
        //this is my array which i use to make pictures of 8 coins
        for (int i = 0; i < coin.length; i++) {
            coin[i] = new coins();
            coin[i].image = Toolkit.getDefaultToolkit().getImage("coin.png");
            coin[i].hitbox = new Rectangle(coin[i].xpos, coin[i].ypos, coin[i].width, coin[i].height);
            coin[i].hitbox.width = 55;
            coin[i].hitbox.height = 55;
        }
        //creates the images

        coin[0].xpos = 320;
        coin[0].ypos = 360;
        coin[0].hitbox = new Rectangle(coin[0].xpos, coin[0].ypos, coin[0].width, coin[0].height);
        coin[1].xpos = 675;
        coin[1].ypos = 360;
        coin[1].hitbox = new Rectangle(coin[1].xpos, coin[1].ypos, coin[1].width, coin[1].height);
        coin[2].xpos = 920;
        coin[2].ypos = 360;
        coin[2].hitbox = new Rectangle(coin[2].xpos, coin[2].ypos, coin[2].width, coin[2].height);
        coin[3].xpos = 150;
        coin[3].ypos = 210;
        coin[3].hitbox = new Rectangle(coin[3].xpos, coin[3].ypos, coin[3].width, coin[3].height);
        coin[4].xpos = 750;
        coin[4].ypos = 120;
        coin[4].hitbox = new Rectangle(coin[4].xpos, coin[4].ypos, coin[4].width, coin[4].height);
        coin[5].xpos = 680;
        coin[5].ypos = 120;
        coin[5].hitbox = new Rectangle(coin[5].xpos, coin[5].ypos, coin[5].width, coin[5].height);
        coin[6].xpos = 470;
        coin[6].ypos = 360;
        coin[6].hitbox = new Rectangle(coin[6].xpos, coin[6].ypos, coin[6].width, coin[6].height);
        coin[7].xpos = 800;
        coin[7].ypos = 210;
        coin[7].hitbox = new Rectangle(coin[7].xpos, coin[7].ypos, coin[7].width, coin[7].height);
        //defines the location of the coins and what their hitbox is.
//
    }// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		Mario1.move();
        crashing();
        Gravity();
        mushroom1.move();
        turtle1.move();
        //calls characters so they can move

	}



    public void crashing(){
        //this is where all the crashes are defined, as in the intersections
        if(Mario1.hitbox.intersects(pipe1.hitbox)){
            System.out.println("CRASH!!");
            Mario1.xpos = Mario1.xpos-50;
        }

        if(Mario1.hitbox.intersects(pipe2.hitbox)){
            System.out.println("CRASH!!");
            Mario1.xpos = Mario1.xpos-50;
        }
        //this and the one above bounces the mario off the sides of the pipes

        if(Mario1.hitbox.intersects(pipeteleport2.hitbox)){
            System.out.println("TELEPORT");
            Mario1.xpos = 170;
            Mario1.ypos = 435;
        }

        if(Mario1.hitbox.intersects(pipeteleport.hitbox)){
            System.out.println("TELEPORT");

            Mario1.xpos = 570;
            Mario1.ypos = pipeteleport.ypos - 250;
        }
        //this and the one above can make mario teleport from one pipe to the other

        if (Mario1.hitbox.intersects(coin[0].hitbox)) {
        coin[0].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[1].hitbox)) {
            coin[1].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[2].hitbox)) {
            coin[2].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[3].hitbox)) {
            coin[3].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[4].hitbox)) {
            coin[4].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[5].hitbox)) {
            coin[5].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[6].hitbox)) {
            coin[6].isAlive=false;
            System.out.println("collected 1 coin");
        }
        if (Mario1.hitbox.intersects(coin[7].hitbox)) {
            coin[7].isAlive=false;
            System.out.println("collected 1 coin");
        }
        //this and coins above define the is alive as false so the coins disappear after intersecting mario

        if(Mario1.hitbox.intersects(mushroom1.hitbox)){
            System.out.println("bad luck");
            Mario1.height = Mario1.height-10;
            Mario1.width = Mario1.width-10;

        }
        //the mario grows smaller when it intersect the moving mushroom.


      if (Mario1.hitbox.intersects(goodMushroom.hitbox)){
            Mario1.height = 70;
            Mario1.width = 70;
            Mario1.hitbox = new Rectangle(Mario1.xpos, Mario1.ypos, Mario1.width, Mario1.height);
        }
      //if intersect the green mushroom, mario grows

    }

    public void Gravity(){
        //this makes it so the mario falls when he is in mid-air.
        if (!Mario1.onGround){
            Mario1.vSpeed+= Mario1.gravity;
        }

        Mario1.ypos+=Mario1.vSpeed;

        if (Mario1.ypos>=550){
            Mario1.ypos = 550;
            Mario1.vSpeed = 0;
            Mario1.onGround = true;
        }

        //this and the following if statements make it so there is no gravity on the bricks.
        if(Mario1.hitbox.intersects(brick1.hitbox) ){
            Mario1.dy = 0;
            Mario1.ypos = brick1.ypos-Mario1.height+1;
            System.out.println(brick1.ypos);
            Mario1.onGround = true;
        }

        if (!Mario1.hitbox.intersects(brick1.hitbox) && !Mario1.hitbox.intersects(brick2.hitbox) && !Mario1.hitbox.intersects(brick3.hitbox) && !Mario1.hitbox.intersects(brick4.hitbox) && !Mario1.hitbox.intersects(brick5.hitbox) && !Mario1.hitbox.intersects(brick6.hitbox) && !Mario1.hitbox.intersects(brick7.hitbox)){
            Mario1.onGround = false;

        }
        if(Mario1.hitbox.intersects(brick2.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick2.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick3.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick3.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick4.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick4.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick5.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick5.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick6.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick6.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick7.hitbox)){
            Mario1.dy = 0;
            Mario1.ypos = brick7.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }

    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
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
      canvas.addKeyListener(this);
      canvas.addMouseListener(this);
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


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image and the hitboxes of the characters and objects and background
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

        g.drawImage(mushroomPic, mushroom1.xpos, mushroom1.ypos, mushroom1.width, mushroom1.height, null);
        g.drawRect(mushroom1.hitbox.x, mushroom1.hitbox.y, mushroom1.hitbox.width, mushroom1.hitbox.height);
        //image and hitbox of mushroom character

        if(!Mario1.hitbox.intersects(turtle1.hitbox) && turtle1.isAlive ==true){
        g.drawImage(turtlePic, turtle1.xpos, turtle1.ypos, turtle1.width, turtle1.height, null);}
        if(Mario1.hitbox.intersects(turtle1.hitbox) || turtle1.isAlive == false ){
            g.drawImage(squishedturtlePic, turtle1.xpos, turtle1.ypos, turtle1.width, turtle1.height, null);
            turtle1.isAlive = false;
        }
        //draws the image and hitbox and chage of image of the turtle.


        //draws image of mario
        g.drawImage(MarioPic, Mario1.xpos, Mario1.ypos, Mario1.width, Mario1.height, null);

        /*gives a visual of how the hitbox looks */
        g.drawRect(turtle1.hitbox.x, turtle1.hitbox.y, turtle1.hitbox.width, turtle1.hitbox.height);

        g.drawRect(pipe1.hitbox.x, pipe1.hitbox.y, pipe1.hitbox.width, pipe1.hitbox.height);
        g.drawRect(pipe2.hitbox.x, pipe2.hitbox.y, pipe2.hitbox.width, pipe2.hitbox.height);
        g.drawRect(pipeteleport.hitbox.x, pipeteleport.hitbox.y, pipeteleport.hitbox.width, pipeteleport.hitbox.height);
        g.drawRect(pipeteleport2.hitbox.x, pipeteleport2.hitbox.y, pipeteleport2.hitbox.width, pipeteleport2.hitbox.height);

        g.drawRect(Mario1.hitbox.x, Mario1.hitbox.y, Mario1.hitbox.width, Mario1.hitbox.height);
        g.drawRect(goodMushroom.hitbox.x, goodMushroom.hitbox.y, goodMushroom.hitbox.width, goodMushroom.hitbox.height);
        g.drawRect(mushroom1.hitbox.x, mushroom1.hitbox.y, mushroom1.hitbox.width, mushroom1.hitbox.height);

        g.drawRect(brick1.hitbox.x, brick1.hitbox.y, brick1.width, brick1.height);
        g.drawRect(brick2.hitbox.x, brick2.hitbox.y, brick2.width, brick2.height);
        g.drawRect(brick3.hitbox.x, brick3.hitbox.y, brick3.width, brick3.height);
        g.drawRect(brick4.hitbox.x, brick4.hitbox.y, brick4.width, brick4.height);
        g.drawRect(brick5.hitbox.x, brick5.hitbox.y, brick5.width, brick5.height);
        g.drawRect(brick6.hitbox.x, brick6.hitbox.y, brick6.width, brick6.height);
        g.drawRect(brick7.hitbox.x, brick7.hitbox.y, brick7.width, brick7.height);

        //draws the coins if they are alive
        if (coin[0].isAlive==true) {
            g.drawImage(coin[0].image, coin[0].xpos, coin[0].ypos, 55, 55, null);
        }
        if (coin[1].isAlive==true) {
        g.drawImage(coin[1].image,coin[1].xpos, coin[1].ypos,55,55, null);}
        if (coin[2].isAlive==true) {
        g.drawImage(coin[2].image,coin[2].xpos, coin[2].ypos,55,55, null);}
        if (coin[3].isAlive==true) {
        g.drawImage(coin[3].image,coin[3].xpos, coin[3].ypos,55,55, null);}
        if (coin[4].isAlive==true) {
        g.drawImage(coin[4].image,coin[4].xpos, coin[4].ypos,55,55, null);}
        if (coin[5].isAlive==true) {
        g.drawImage(coin[5].image,coin[5].xpos, coin[5].ypos,55,55, null);}
        if (coin[6].isAlive==true) {
        g.drawImage(coin[6].image,coin[6].xpos, coin[6].ypos,55,55, null);}
        if (coin[7].isAlive==true) {
        g.drawImage(coin[7].image,coin[7].xpos, coin[7].ypos,55,55, null);}

        //draws the hitboxes of the coins
        g.drawRect(coin[0].hitbox.x, coin[0].hitbox.y, coin[0].hitbox.width, coin[0].hitbox.height);
        g.drawRect(coin[1].hitbox.x, coin[1].hitbox.y, coin[1].hitbox.width, coin[1].hitbox.height);
        g.drawRect(coin[2].hitbox.x, coin[2].hitbox.y, coin[2].hitbox.width, coin[2].hitbox.height);
        g.drawRect(coin[3].hitbox.x, coin[3].hitbox.y, coin[3].hitbox.width, coin[3].hitbox.height);
        g.drawRect(coin[4].hitbox.x, coin[4].hitbox.y, coin[4].hitbox.width, coin[4].hitbox.height);
        g.drawRect(coin[5].hitbox.x, coin[5].hitbox.y, coin[5].hitbox.width, coin[5].hitbox.height);
        g.drawRect(coin[6].hitbox.x, coin[6].hitbox.y, coin[6].hitbox.width, coin[6].hitbox.height);
        g.drawRect(coin[7].hitbox.x, coin[7].hitbox.y, coin[7].hitbox.width, coin[7].hitbox.height);

       //only draws the start image if the mouse hasn't entered
        if (pressStart.isAlive == true){
            g.drawImage(start, 0, 0, WIDTH, HEIGHT, null);
        }

        if (coin[0].isAlive==false && coin[1].isAlive==false && coin[2].isAlive==false && coin[3].isAlive==false && coin[4].isAlive==false && coin[5].isAlive==false && coin[6].isAlive==false && coin[7].isAlive==false){
            g.drawImage(gameOver, 0, 0, WIDTH, HEIGHT, null);

        }
        //when all coins are collected the game over screen apears.


		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println("key typed " + e.getKeyCode());
        if (e.getKeyCode()==38){ // moves mario up by making him jump
            System.out.println("pressed up arrow");
            Mario1.vSpeed = -10;
            Mario1.onGround = false;
            Mario1.ypos--;
        }
        if (e.getKeyCode()==40){ //moves mario down faster
            System.out.println("pressed down arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dy= 5;
        }
        if (e.getKeyCode()==39){ // moves mario to the right
            System.out.println("pressed right arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= 5;
        }
        if (e.getKeyCode()==37){ //moves mario to the left
            System.out.println("pressed left arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= -5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //these are all so that they dont keep moving after the key is released
        if (e.getKeyCode()==38){
            System.out.println(" not pressed up arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dy= 0;
        }
        if (e.getKeyCode()==40){
            System.out.println("pressed down arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dy= 0;
        }
        if (e.getKeyCode()==39){
            System.out.println("pressed right arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= 0;
        }
        if (e.getKeyCode()==37){
            System.out.println("pressed left arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= 0;
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint());
        Mario1.xpos =e.getX();
        Mario1.ypos = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //the start screen will disapear when the mouse enters
        System.out.println("Entered!!!");
        pressStart.isAlive = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {



    }



}