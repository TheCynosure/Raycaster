import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jack on 12/14/16.
 */
public class Canvas  extends JPanel implements KeyListener, ComponentListener {

    private Player player;
    private Image sky;

    public Canvas() {
        //Put player at the center of the map
        player = new Player(World.map[0].length * World.MAP_TILE_SIZE / 2, World.map.length * World.MAP_TILE_SIZE / 2);
        this.addComponentListener(this);;
        try {
            sky = ImageIO.read(new File("Sky.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Drawing Sky
        g2.drawImage(sky, 0, 0, null);

        //Drawing ground
        g2.setColor(Color.MAGENTA);
        g2.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        //Draw Map
        g2.setColor(Color.GRAY);
        for(int row = 0; row < World.map.length; row++) {
            for(int col = 0; col < World.map[0].length; col++) {
                if (World.map[row][col] > 0) {
                    g2.fillRect(col * World.MAP_TILE_SIZE, row * World.MAP_TILE_SIZE, World.MAP_TILE_SIZE, World.MAP_TILE_SIZE);
                } else {
                    g2.drawRect(col * World.MAP_TILE_SIZE, row * World.MAP_TILE_SIZE, World.MAP_TILE_SIZE, World.MAP_TILE_SIZE);
                }
            }
        }

        player.draw(g2, getWidth(), getHeight());
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int moveSpeed = 5;
        if(e.getKeyCode() == KeyEvent.VK_A) {
            player.rotation -= 5;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            player.rotation += 5;
        }
        player.rotation = Util.SignModulo((int)player.rotation, 360);

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.y += Math.sin(Math.toRadians(Util.SignModulo(player.rotation - 90, 360))) * moveSpeed;
            player.x += Math.cos(Math.toRadians(Util.SignModulo(player.rotation - 90, 360))) * moveSpeed;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.y += Math.sin(Math.toRadians(Util.SignModulo(player.rotation + 90, 360))) * moveSpeed;
            player.x += Math.cos(Math.toRadians(Util.SignModulo(player.rotation + 90, 360))) * moveSpeed;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.y += Math.sin(Math.toRadians(player.rotation)) * moveSpeed;
            player.x += Math.cos(Math.toRadians(player.rotation)) * moveSpeed;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.y += Math.sin(Math.toRadians(Util.SignModulo(180 + player.rotation, 360))) * moveSpeed;
            player.x += Math.cos(Math.toRadians(Util.SignModulo(player.rotation + 180, 360))) * moveSpeed;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {
        player.scanLines = getWidth();
        sky = sky.getScaledInstance(getWidth(), getHeight() / 2, Image.SCALE_DEFAULT);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
