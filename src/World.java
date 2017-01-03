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
            regularWallTex = ImageIO.read(new File("WallTexture.jpg"));
            Image image = regularWallTex.getScaledInstance(1000, 1000, Image.SCALE_DEFAULT);
            regularWallTex.getGraphics().drawImage(image, 0, 0, null);
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

    public static BufferedImage getSubTexture(WallInfo wallInfo, int WALL_HEIGHT, int WALL_WIDTH) {
        switch (wallInfo.type) {
            case 1:
                if(wallInfo.side == WallInfo.Side.HORIZONTAL) {
                    BufferedImage tex = regularWallTex.getSubimage(wallInfo.intersectionPoint.x % World.MAP_TILE_SIZE, 0, WALL_WIDTH, regularWallTex.getHeight());
                    return (BufferedImage) tex.getScaledInstance(tex.getWidth(), WALL_HEIGHT, Image.SCALE_DEFAULT);
                } else {
                    //Vertical
                    BufferedImage tex = regularWallTex.getSubimage(0, wallInfo.intersectionPoint.y  % World.MAP_TILE_SIZE, WALL_WIDTH, regularWallTex.getHeight());
                    return (BufferedImage) tex.getScaledInstance(tex.getWidth(), WALL_HEIGHT, Image.SCALE_DEFAULT);
                }
            default:
                return null;

        }
    }


}
