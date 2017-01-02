import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by jack on 12/14/16.
 */
public class Canvas  extends Panel implements KeyListener {

    private Player player;

    public Canvas() {
        //Put player at the center of the map
        player = new Player(World.map[0].length * World.MAP_TILE_SIZE / 2, World.map.length * World.MAP_TILE_SIZE / 2);
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        g.setColor(Color.GRAY);
        //Draw Map
        for(int row = 0; row < World.map.length; row++) {
            for(int col = 0; col < World.map[0].length; col++) {
                if (World.map[row][col] > 0) {
                    g.fillRect(col * World.MAP_TILE_SIZE, row * World.MAP_TILE_SIZE, World.MAP_TILE_SIZE, World.MAP_TILE_SIZE);
                } else {
                    g.drawRect(col * World.MAP_TILE_SIZE, row * World.MAP_TILE_SIZE, World.MAP_TILE_SIZE, World.MAP_TILE_SIZE);
                }
            }
        }

        player.draw(g, getWidth(), getHeight());
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            player.rotation -= 5;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            player.rotation += 5;
        }
        player.rotation = Util.SignModulo((int)player.rotation, 360);

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.x -= 5;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.x += 5;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.y -= 5;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.y += 5;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
