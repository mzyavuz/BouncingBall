/**
 * This program demonstrates an animation of
 * a bouncing ball within a canvas
 * that also contains a rectangular box.
 *
 * @author M.Zeynep Çakmakcı, Student ID: 2024719030
 * @since Date: 09.10.2024
 */

import java.util.Random;

public class MelikeZeynepCakmakci {
    /**
     * The main method initializes the canvas and
     * runs the animation loop.
     * @param args Main input arguments are not used.
     */
    public static void main(String[] args) {

        // Set up the canvas
        StdDraw.setCanvasSize(1200, 800); // Set the size of the canvas
        StdDraw.setXscale(-1.5, 1.5); // Set the scale of the coordinate system
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering(); // Use for faster animations
        int pauseDuration = 15; // Pause duration in milliseconds

        double radius = 0.05;  // Radius of the ball
        double positionX = 0 + radius, positionY = -1 + radius;  // Initial (x,y) ball position

        Random random = new Random();
        int velocityAngle = random.nextInt(180);
        double velocityMagnitude = 0.03;
        double velocityX = velocityMagnitude * Math.cos(Math.toDegrees(velocityAngle));
        double velocityY = velocityMagnitude * Math.sin(Math.toDegrees(velocityAngle));

        // The components of the Box
        double boxPositionX = 0.0, boxPositionY = 0.0; // (x,y) Box position
        double widthLength = 0.5, heightLength = 0.2; // The length of the sides

        while (true) { // Main animation loop
            // Bounce off wall according to law of elastic collision
            if (Math.abs(positionX + velocityX) > 1.5 - radius) //When hit right or left
                velocityX = -velocityX;
            if (Math.abs(positionY + velocityY) > 1.0 - radius) //When hit bottom or up
                velocityY = -velocityY;

            // Bounce off box according to law of elastic collision
            if (Math.abs(positionY + velocityY - boxPositionY) < heightLength / 2 + radius
                    && Math.abs(positionX + velocityX - boxPositionX) < widthLength / 2 + radius) {

                double overlapX = Math.abs(positionX + velocityX - boxPositionX) - widthLength / 2;
                double overlapY = Math.abs(positionY + velocityY - boxPositionY) - heightLength / 2;

                // Check where the hit occurs
                if (overlapX > overlapY) {
                    velocityX = -velocityX; // Reverse X velocity
                } else {
                    velocityY = -velocityY; // Reverse Y velocity
                }
            }

            positionX = positionX + velocityX; // Update x position
            positionY = positionY + velocityY; // Update y position

            StdDraw.clear(StdDraw.WHITE); // Clear the background
            StdDraw.setPenColor(StdDraw.BLUE); // To draw blue box
            StdDraw.filledRectangle(boxPositionX, boxPositionY, widthLength / 2, heightLength / 2);
            StdDraw.setPenColor(StdDraw.BLACK); // To draw black ball on the screen
            StdDraw.filledCircle(positionX, positionY, radius);
            StdDraw.show(); // Show the drawing on the screen
            StdDraw.pause(pauseDuration); // Pause the drawing at each iteration
        }
    }
}
