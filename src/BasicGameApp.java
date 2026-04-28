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
    public Image coin;
    public Image coin2;
    public Image coin3;
    public Image coin4;
    public Image coin5;
    public Image coin6;
    public Image coin7;
    public Image coin8;


    public Image turtlePic;
   // public Image mushroom1;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
    private Mario Mario1;
    private Pipe pipe1;
    private Pipe pipe2;
    private Pipe pipeteleport;
    private Pipe pipeteleport2;
    private bricks brick1;
    private bricks brick2;
    private Mushroom mushroom1;
    private Turtle turtle1;

    public coins[] coin1;
    //private coins coi1n;



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
        coin = Toolkit.getDefaultToolkit().getImage("coin.png");




        Mario1 = new Mario(10,100);
        mushroom1 = new Mushroom(200,560);
        turtle1 = new Turtle(500, 550);
        pipe1 = new Pipe(170, 550);
        pipe1.height = 200;
        pipe1.hitbox = new Rectangle(pipe1.xpos, pipe1.ypos, pipe1.width, pipe1.height);
        pipe2 = new Pipe(570, 448);
        pipe2.height = 400;
        pipe2.hitbox = new Rectangle(pipe2.xpos, pipe2.ypos, pipe2.width, pipe2.height);

        brick1 = new bricks(160,260);
        brick1.height = 50;
        brick1.width = 170;
        brick1.hitbox = new Rectangle(brick1.xpos, brick1.ypos, brick1.width, brick1.height);

        brick2 = new bricks(325,410);
        brick2.height = 40;
        brick2.width = 185;
        brick2.hitbox = new Rectangle(brick2.xpos, brick2.ypos, brick2.width, brick2.height);


        pipeteleport = new Pipe(170, 510);
        pipeteleport.height = 40;
        pipeteleport.hitbox = new Rectangle(pipeteleport.xpos, pipeteleport.ypos, pipeteleport.width, pipeteleport.height);

        pipeteleport2 = new Pipe(570, 408);
        pipeteleport2.height = 50;
        pipeteleport2.hitbox = new Rectangle(pipeteleport2.xpos, pipeteleport2.ypos, pipeteleport2.width, pipeteleport2.height);

        MarioPic = Toolkit.getDefaultToolkit().getImage("mario.png"); //load the picture
        mushroomPic = Toolkit.getDefaultToolkit().getImage("mushroom.png");
        turtlePic = Toolkit.getDefaultToolkit().getImage("Turtle.png");//load the picture

        coin1 = new coins[7];

        coin1[0] =
        coin1[1] =
        coin1[2] =
        coin1[3] =
        coin1[4] =
        coin1[5] =
        coin1[6] =
        coin1[7] =


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
	}



    public void crashing(){
        //check to see if my astro's crash into each other
        if(Mario1.hitbox.intersects(pipe1.hitbox)){
            System.out.println("CRASH!!");
            Mario1.xpos = Mario1.xpos-50;
        }

        if(Mario1.hitbox.intersects(pipe2.hitbox)){
            System.out.println("CRASH!!");
            Mario1.xpos = Mario1.xpos-50;
        }

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

      // if (){

       //}


    }

    public void Gravity(){

        if (!Mario1.onGround){
            Mario1.vSpeed+= Mario1.gravity;
        }

        Mario1.ypos+=Mario1.vSpeed;

        if (Mario1.ypos>=550){
            Mario1.ypos = 550;
            Mario1.vSpeed = 0;
            Mario1.onGround = true;
        }
        if(Mario1.hitbox.intersects(brick1.hitbox) ){
            System.out.println("on brick 1");

            // Mario1.width = Mario1.width+ 10;
            Mario1.dy = 0;
            Mario1.ypos = brick1.ypos-Mario1.height+1;
            System.out.println(brick1.ypos);
            Mario1.onGround = true;
        }

        if (!Mario1.hitbox.intersects(brick1.hitbox) && !Mario1.hitbox.intersects(brick2.hitbox)){
            Mario1.onGround = false;
            //Mario1.isCrashingBeick1= false;
        }
        if(Mario1.hitbox.intersects(brick2.hitbox)){
            System.out.println("brick 2");
            Mario1.dy = 0;
            Mario1.ypos = brick2.ypos-Mario1.height+1;
            Mario1.onGround = true;
        }
       // if (!Mario1.hitbox.intersects(brick2.hitbox)){
         //   Mario1.onGround = false;
        //}

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

      //draw the image of the astronaut
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(mushroomPic, mushroom1.xpos, mushroom1.ypos, mushroom1.width, mushroom1.height, null);
        g.drawImage(turtlePic, turtle1.xpos, turtle1.ypos, turtle1.width, turtle1.height, null);
      if (!Mario1.hitbox.intersects(coi1n.hitbox)) {
          g.drawImage(coin, 150, 190, 55, 55, null);}

      if (!Mario1.hitbox.intersects(coi2n.hitbox)) {
            g.drawImage(coin2, 350, 360, 55, 55, null);}

      if (!Mario1.hitbox.intersects(coi3n.hitbox)) {
            g.drawImage(coin3,680, 360,55,55, null);}

        g.drawImage(coin4,785, 360,55,55, null);
        g.drawImage(coin5,950, 360,55,55, null);
        g.drawImage(coin6,150, 190,55,55, null);
        g.drawImage(coin7,150, 190,55,55, null);
        g.drawImage(coin8,150, 190,55,55, null);
        g.drawImage(MarioPic, Mario1.xpos, Mario1.ypos, Mario1.width, Mario1.height, null);

        g.drawRect(pipe1.hitbox.x, pipe1.hitbox.y, pipe1.hitbox.width, pipe1.hitbox.height);
        g.drawRect(pipe2.hitbox.x, pipe2.hitbox.y, pipe2.hitbox.width, pipe2.hitbox.height);
        /*gives a visual of how the hitbox looks */
        g.drawRect(pipeteleport.hitbox.x, pipeteleport.hitbox.y, pipeteleport.hitbox.width, pipeteleport.hitbox.height);
        g.drawRect(pipeteleport2.hitbox.x, pipeteleport2.hitbox.y, pipeteleport2.hitbox.width, pipeteleport2.hitbox.height);

        g.drawRect(Mario1.hitbox.x, Mario1.hitbox.y, Mario1.hitbox.width, Mario1.hitbox.height);

        g.drawRect(brick1.hitbox.x, brick1.hitbox.y, 170, 50);
        g.drawRect(brick2.hitbox.x, brick2.hitbox.y, brick2.width, brick2.height);

		g.dispose();

		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println("key typed " + e.getKeyCode());
        if (e.getKeyCode()==38){
            System.out.println("pressed up arrow");
            Mario1.vSpeed = -10;
            Mario1.onGround = false;
            Mario1.ypos--;
        }
        if (e.getKeyCode()==40){
            System.out.println("pressed down arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dy= 5;
        }
        if (e.getKeyCode()==39){
            System.out.println("pressed right arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= 5;
        }
        if (e.getKeyCode()==37){
            System.out.println("pressed left arrow");
            // astro.ypos = astro.ypos-20;
            Mario1.dx= -5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
        System.out.println("Entered!!!");

    }

    @Override
    public void mouseExited(MouseEvent e) {



    }



}