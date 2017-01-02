import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

/**
 * Created by jack on 12/14/16.
 */
public class Window {
    public static void main(String[] args) {
        Window window = new Window();
    }

    public Window() {
        JFrame jFrame = new JFrame("Raycaster");
        jFrame.setSize(World.map[0].length * World.MAP_TILE_SIZE, World.map.length * World.MAP_TILE_SIZE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        jFrame.add(canvas);
        jFrame.addKeyListener(canvas);
        jFrame.setIgnoreRepaint(true);
        jFrame.setVisible(true);
    }
}
