import java.awt.*;



public class Mushroom {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public Mushroom(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =2;
        dy =0;
        width = 50;
        height =50;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        if(xpos>1000){xpos=0-width;} //wrap when hits right wall
        if(xpos<0-width){xpos=1000;} //wrap when hits left wall
        if(ypos>700){ypos=0-height;} //wrap when hits bottom wall
        if(ypos<0-height){ypos=700;} //wrap when hits top wall
       //code to make it bounce:
        // if (xpos>1000){dx=-dx;}
        //if (ypos>700){dy=-dy;}
        //if (xpos<0){dx=-dx;}
        //if (ypos<0){dy=-dy;}
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }
}
