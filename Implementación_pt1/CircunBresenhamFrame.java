import javax.swing.*;
import java.awt.*;


class CircunBresenhamFrame extends JPanel {
    int maxX, maxY;


    void dibujarCircunBresenhamFrame(Graphics g, int x0, int y0, int radio) {
        int x = 0;
        int y = radio;
        int d = 3 - 2 * radio;
       
        while (x <= y) {
            g.fillOval(x0 + x, maxY - (y0 + y), 5, 5);
            g.fillOval(x0 + y, maxY - (y0 + x), 5, 5);
            g.fillOval(x0 + x, maxY - (y0 - y), 5, 5);
            g.fillOval(x0 + y, maxY - (y0 - x), 5, 5);
            g.fillOval(x0 - x, maxY - (y0 + y), 5, 5);
            g.fillOval(x0 - y, maxY - (y0 + x), 5, 5);
            g.fillOval(x0 - x, maxY - (y0 - y), 5, 5);
            g.fillOval(x0 - y, maxY - (y0 - x), 5, 5);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y = y - 1;
            }
            x++;
        }
    }


    void dibujarEjes(Graphics g) {
        g.drawLine(0, maxY, maxX, maxY);
        g.drawLine(0, 0, 0, maxY);


       
        for (int i = 1; i <= 50; i++) {
            g.drawString(Integer.toString(i), i * 20, maxY - 5);
            g.drawString(Integer.toString(i), 5, maxY - i * 20);
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
        dibujarEjes(g);
        long startTime = System.nanoTime();
        dibujarCircunBresenhamFrame(g, 401, 401, 200);
        long endTime = System.nanoTime();
        dibujarRadio(g, 401, 401, 200);
        long duration = (endTime - startTime) / 1000000;
        g.setFont(new Font("Arial", Font.PLAIN, 12)); 
        g.drawString("Tiempo de ejecución: " + duration + " ms", maxX - 200, 20); 
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new CircunBresenhamFrame());
        frame.setVisible(true);
    }
}
