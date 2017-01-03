import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jack on 12/14/16.
 */
public class Player {
    int x, y;
    double rotation;
    int FOV = 60;
    int scanLines = 600;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        rotation = 0;
    }

    public void draw(Graphics graphics, int width, int height) {
        //Easy to see
        graphics.setColor(Color.RED);
        //Draw player
        graphics.fillOval(x - 5, y - 5, 10, 10);
        //Draw Walls
        drawWalls((Graphics2D) graphics, width, height);
    }

    private void drawWalls(Graphics2D graphics2D, int width, int height) {
        int scanLineIndex = 0;
        int[] xPoints = new int[scanLines + 2];
        int[] yPoints = new int[scanLines + 2];

        xPoints[0] = x;
        yPoints[0] = y;

        final int WALL_WIDTH = width / scanLines;

        for(double rotChange = FOV / 2; scanLineIndex <= scanLines; rotChange -= (double)(FOV) / scanLines) {
            double localRot = rotation - rotChange;
            localRot = Util.SignModulo(localRot, 360);
            graphics2D.setColor(Color.RED);
            Raycast ray = new Raycast(x,y,localRot);
            WallInfo intersectionWall = ray.getClosestWallIntersection();

            int distY = intersectionWall.intersectionPoint.y - y;
            int distX = intersectionWall.intersectionPoint.x - x;

            double distToWall = Math.sqrt(distX * distX + distY * distY);

            //Got this formula from Permadi Tutorial (http://permadi.com/1996/05/ray-casting-tutorial-9/)
            int wallHeight = (int) ((World.WALL_HEIGHT * World.SCALE_FACTOR) / distToWall);

            //Change color based on type and orientation of wall
//            graphics2D.setColor(World.getColorFromWall(intersectionWall));

            //Draw from the middle of the screen
//            graphics2D.fillRect(WALL_WIDTH * scanLineIndex, (height / 2) - (wallHeight / 2), WALL_WIDTH, wallHeight);

            graphics2D.drawImage(World.getSubTexture(intersectionWall, WALL_WIDTH, wallHeight), WALL_WIDTH * scanLineIndex, (height / 2) - (wallHeight / 2), null);

            scanLineIndex++;

            xPoints[scanLineIndex] = intersectionWall.intersectionPoint.x;
            yPoints[scanLineIndex] = intersectionWall.intersectionPoint.y;

        }

        graphics2D.setColor(new Color(100, 100, 100, 100));
        graphics2D.fillPolygon(xPoints, yPoints, scanLineIndex);
    }

}
