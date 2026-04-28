import java.awt.*;

public class coins { public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
                       //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;

    public coins() {
        xpos = 0;
        ypos = 0;
        width = 55;
        height = 55;
        isAlive = true;

        hitbox = new Rectangle(xpos, ypos, width, height);

    } // constructor
}
