package Fabian;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


class CircunferenciaReal extends JPanel {
    int maxX, maxY;
    int x0 = 401;
    int y0 = 401;
    int radio = 200;
    Timer timer;


    List<Point> points = new ArrayList<>();


    CircunferenciaReal() {
        timer = new Timer(100, new ActionListener() {
            int x = -radio;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x <= radio) {
                    int y = (int) Math.sqrt(radio * radio - x * x);
                    points.add(new Point(x0 + x, maxY - (y0 + y)));
                    points.add(new Point(x0 + x, maxY - (y0 - y)));
                    repaint();
                    x++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }


    void dibujarCircunferenciaReal(Graphics g) {
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
        dibujarCircunferenciaReal(g);
        dibujarRadio(g, x0, y0, radio);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new CircunferenciaReal());
        frame.setVisible(true);
    }
}
