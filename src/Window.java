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
        jFrame.setSize(600, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        jFrame.add(canvas);
        jFrame.addKeyListener(canvas);
        jFrame.setIgnoreRepaint(true);
        jFrame.setVisible(true);

        Timer timer = new Timer(17, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });

        timer.start();
    }
}
