import java.awt.*;

/**
 * Created by jack on 12/14/16.
 */
public class World {
    public static final int map[][] = {
            {1,1,1,1,1,1,1,1,1},
            {1,0,0,0,1,0,1,0,1},
            {1,0,1,0,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1}
    };

    public static final int MAP_TILE_SIZE = 20;
    public static final int WALL_HEIGHT = 30;
    public static final int SCALE_FACTOR = 200;

    public static Color getColorFromWall(WallInfo wallInfo) {
        switch (wallInfo.type) {
            case 1:
                if(wallInfo.side == WallInfo.Side.HORIZONTAL) {
                    return new Color(200,0, 9);
                } else {
                    //Vertical
                    return new Color(127, 0,0);
                }
            default:
                return Color.WHITE;

        }
    }


}
