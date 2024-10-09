import java.util.Random;

public class MelikeZeynepCakmakci {

    public static void main(String[] args) {

        StdDraw.setCanvasSize(1200, 800); // set the size of the drawing canvas
        StdDraw.setXscale(-1.5, 1.5); // set the scale of the coordinate system
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering(); // Use for faster animations

        int pauseDuration = 15; // pause duration in milliseconds (How fast is the ball)

        double position_x = 0, position_y = -0.98;  // initial (x,y) ball position

        Random random = new Random();
        int velocityAngle = random.nextInt(180);
        double velocityMagnitude = 0.03;
        double velocity_x = velocityMagnitude * Math.cos(velocityAngle);
        double velocity_y = velocityMagnitude * Math.sin(velocityAngle);  // initial velocity components
        double radius = 0.05;  // radius of the ball

        double boxPositionX = 0.0;
        double boxPositionY = 0.0;

        double widthLength = 0.5;
        double heightLength = 0.2;

        while (true) { // main animation loop
            // bounce off wall according to law of elastic collision
            if (Math.abs(position_x + velocity_x) > 1.5 - radius) //When hit right or left
                velocity_x = -velocity_x;
            if (Math.abs(position_y + velocity_y) > 1.0 - radius) //When hit bottom or up
                velocity_y = -velocity_y;

            if (Math.abs(position_y + velocity_y - boxPositionY) < heightLength / 2 + radius
                    && Math.abs(position_x + velocity_x - boxPositionX) < widthLength / 2 + radius) {
                if (Math.abs(position_y + velocity_y - boxPositionY) < heightLength / 2 + radius / 2)
                    velocity_x = -velocity_x;
                else
                    velocity_y = -velocity_y;
            }

            position_x = position_x + velocity_x; // update positions
            position_y = position_y + velocity_y;

            StdDraw.clear(StdDraw.WHITE); // clear the background - Background is White
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledRectangle(boxPositionX, boxPositionY, widthLength / 2, heightLength / 2);
            StdDraw.setPenColor(StdDraw.BLACK); // draw ball on the screen - Black pen to draw ball
            StdDraw.filledCircle(position_x, position_y, radius); //Black ball
            StdDraw.show(); // show the drawing on the screen
            StdDraw.pause(pauseDuration); // pause the drawing at each iteration
        }
    }
}
