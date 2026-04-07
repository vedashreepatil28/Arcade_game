import java.awt.*;

public class Pipe {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public Pipe(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        width = 70;
        height = 60;
        isAlive = true;

        hitbox = new Rectangle(xpos, ypos, width, height);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy



}
