import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jack on 1/1/17.
 * Used to find the distance to walls in the World double array.
 */
public class Raycast {

    int x, y;
    double rotation;
    final int pointNullVal = 0;

    public Raycast(int x, int y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Point getClosestWallIntersection() {
        Point[] intersections = getFirstXYIntersections();
        //If either one is not found that it must be the one that was found.
        if(intersections[0].x == pointNullVal && intersections[0].y == pointNullVal) {
            return intersections[1];
        } else if(intersections[1].x == pointNullVal && intersections[1].y == pointNullVal) {
            return intersections[0];
        }

        //Otherwise return the closest point
        if(findDistToPlayer(intersections[0]) > findDistToPlayer(intersections[1])) {
            return intersections[1];
        } else {
            return intersections[0];
        }
    }

    private Point[] getFirstXYIntersections() {
        Point xIntersection = new Point(pointNullVal, pointNullVal);
        Point yIntersection = new Point(pointNullVal, pointNullVal);
        //Cos and Sin use Radians, lets convert!
        double rotationInRadians = Math.toRadians(rotation);
        
        int col, row, xStep, yStep;

        //Deciding side then iterating through all intersections in the direction
        if(rotation <= 270 && rotation >= 90) {
            //LEFT
            col = x / World.MAP_TILE_SIZE;
            xStep = -World.MAP_TILE_SIZE;
        } else {
            //RIGHT
            col = (x / World.MAP_TILE_SIZE) + 1;
            xStep = World.MAP_TILE_SIZE;
        }

        //Same as above but in the y direction
        if(rotation <= 360 && rotation >= 180) {
            //UP
            row = y / World.MAP_TILE_SIZE;
            yStep = -World.MAP_TILE_SIZE;
        } else {
            //DOWN
            row = (y / World.MAP_TILE_SIZE) + 1;
            yStep = World.MAP_TILE_SIZE;
        }

        //Vertical Intersection points
        for(int xCol = col * World.MAP_TILE_SIZE; xCol >= World.MAP_TILE_SIZE && xCol <= (World.map[0].length - 1) * World.MAP_TILE_SIZE; xCol += xStep) {
            int yPoint = (int)( Math.tan(rotationInRadians)  * (xCol - x) + y );
            if(yPoint >= World.MAP_TILE_SIZE && yPoint <= (World.map.length - 1)* World.MAP_TILE_SIZE) {
                Point blockCoords = getBlock(rotation, xCol, yPoint);
                if (World.map[blockCoords.y][blockCoords.x] > 0) {
                    //Intersection on Wall block
                    yIntersection = new Point(xCol, yPoint);
                    break;
                }
            }
        }

        //Horizontal Intersection points
        for(int yRow = row * World.MAP_TILE_SIZE; yRow >= World.MAP_TILE_SIZE && yRow <= (World.map.length - 1) * World.MAP_TILE_SIZE; yRow += yStep) {
            int xPoint = (int)( (yRow - y) / Math.tan(rotationInRadians) ) + x;
            if(xPoint >= World.MAP_TILE_SIZE && xPoint <= (World.map[0].length - 1)* World.MAP_TILE_SIZE) {
                Point blockCoords = getBlock(rotation, xPoint, yRow);
                if (World.map[blockCoords.y][blockCoords.x] > 0) {
                    //Intersection on Wall block
                    xIntersection = new Point(xPoint, yRow);
                    break;
                }
            }
        }

        Point[] points = new Point[2];
        points[0] = xIntersection;
        points[1] = yIntersection;
        return points;
    }

    private Point getBlock(double rotation, int x, int y) {
        double xCoord = (float)(x) / World.MAP_TILE_SIZE;
        double yCoord = (float)(y) / World.MAP_TILE_SIZE;
        //Change the x coord based on left or right
        if(rotation <= 360 && rotation >= 180) {
            //Up
            yCoord = Math.ceil(yCoord);
            yCoord--;
        } else {
            yCoord = Math.floor(yCoord);
        }

        if(rotation <= 270 && rotation >= 90) {
            //Left
            xCoord = Math.ceil(xCoord);
            xCoord--;
        } else {
            xCoord = Math.floor(xCoord);
        }
        return new Point((int)xCoord, (int)yCoord);
    }

    private double findDistToPlayer(Point point) {
        int yDiff = point.y - y;
        int xDiff = point.x - x;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
