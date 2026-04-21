import java.awt.*;

public class bricks {
        public String name;                //holds the name of the hero
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int width;
        public int height;
        public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;

        public bricks(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            width = 70;
            height = 60;
            isAlive = true;

            hitbox = new Rectangle(xpos, ypos, width, height);

        }
    }
