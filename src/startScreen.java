import java.awt.*;

public class startScreen {
        public int xpos;                //the x position
        public int ypos;                //the y position
        Image image;
        public int width;
        public int height;
        public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;

        public startScreen() {
            xpos = 0;
            ypos = 0;
            width = 55;
            height = 55;
            isAlive = true;

            hitbox = new Rectangle(xpos, ypos, width, height);

        } // constructor

}
