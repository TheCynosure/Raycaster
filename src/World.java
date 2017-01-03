import com.sun.javafx.iio.ImageStorage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public static BufferedImage regularWallTex;

    static {
        try {
            regularWallTex = ImageIO.read(new File("WallTexture2.jpg"));
            Image regWallCorrectSize = regularWallTex.getScaledInstance(MAP_TILE_SIZE * 2, MAP_TILE_SIZE * 2, Image.SCALE_SMOOTH);
            BufferedImage tempBuff = new BufferedImage(MAP_TILE_SIZE * 2, MAP_TILE_SIZE * 2, BufferedImage.TYPE_INT_ARGB);
            tempBuff.getGraphics().drawImage(regWallCorrectSize, 0, 0, null);
            regularWallTex = tempBuff;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static Image getSubTexture(WallInfo wallInfo, double WALL_WIDTH, int WALL_HEIGHT) {
        int offset = 0;
        switch (wallInfo.type) {
            case 1:
                if(wallInfo.side == WallInfo.Side.HORIZONTAL) {
                    offset = wallInfo.intersectionPoint.x % World.MAP_TILE_SIZE;
                } else {
                    //Vertical
                    offset = wallInfo.intersectionPoint.y % World.MAP_TILE_SIZE;
                }
                BufferedImage tex = regularWallTex.getSubimage(offset, 0, (int) Math.round(WALL_WIDTH), regularWallTex.getHeight());
                return tex.getScaledInstance(tex.getWidth(), WALL_HEIGHT, Image.SCALE_SMOOTH);
            default:
                return null;

        }
    }


}
