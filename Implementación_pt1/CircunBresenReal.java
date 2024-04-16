import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;




class CircunBresenReal extends JPanel {
    int maxX, maxY;
    int x = 0;
    int y;
    int d;
    int x0 = 401;
    int y0 = 401;
    int radio = 200;
    Timer timer;


List<Point> points = new ArrayList<>();


    CircunBresenReal() {
        y = radio;
        d = 3 - 2 * radio;
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x <= y) {
                    points.add(new Point(x0 + x, maxY - (y0 + y)));
                    points.add(new Point(x0 + y, maxY - (y0 + x)));
                    points.add(new Point(x0 + x, maxY - (y0 - y)));
                    points.add(new Point(x0 + y, maxY - (y0 - x)));
                    points.add(new Point(x0 - x, maxY - (y0 + y)));
                    points.add(new Point(x0 - y, maxY - (y0 + x)));
                    points.add(new Point(x0 - x, maxY - (y0 - y)));
                    points.add(new Point(x0 - y, maxY - (y0 - x)));
                    repaint();
                   
                    if (d < 0) {
                        d = d + 4 * x + 6;
                    } else {
                        d = d + 4 * (x - y) + 10;
                        y = y - 1;
                    }
                    x++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }


    void dibujarCircunBresenReal(Graphics g) {
        for (Point point : points) {
            g.fillOval(point.x, point.y, 5, 5);
        }
     
    }


   
    void dibujarRadio(Graphics g, int x0, int y0, int radio) {
        g.fillOval(x0, maxY - y0, 5, 5);
        g.drawLine(x0, maxY - y0, x0 + radio, maxY - y0);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Radio = " + radio, x0 + radio / 4, maxY - y0 - 10);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
       
        long startTime = System.nanoTime();
        dibujarCircunBresenReal(g);
        long endTime = System.nanoTime();
        dibujarRadio(g, x0, y0, radio);
        long duration = (endTime - startTime) / 1000000;
        g.setFont(new Font("Arial", Font.PLAIN, 12));
       
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new CircunBresenReal());
        frame.setVisible(true);
    }
}
