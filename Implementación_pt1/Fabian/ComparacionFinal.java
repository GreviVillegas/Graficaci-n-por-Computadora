package Fabian;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


class ComparacionFinal extends JPanel {
    int maxX, maxY;
    int x0 = 190;
    int y0 = 350;
    int radio = 175;
    Timer timer;


    List<Point> puntosOriginales = new ArrayList<>();
    List<Point> puntosBresenham = new ArrayList<>();


    ComparacionFinal() {
        timer = new Timer(100, new ActionListener() {
            double angulo = 0;
            int x = 0;
            int y = radio;
            int d = 3 - 2 * radio;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (angulo <= 360) {
                    // Algoritmo de la ecuación original
                    int xOriginal = (int) (radio * Math.cos(Math.toRadians(angulo)));
                    int yOriginal = (int) (radio * Math.sin(Math.toRadians(angulo)));
                    puntosOriginales.add(new Point(x0 + xOriginal, maxY - (y0 + yOriginal)));
                    puntosOriginales.add(new Point(x0 + xOriginal, maxY - (y0 - yOriginal)));


                    // Algoritmo de Bresenham
                    if (x <= y) {
                        puntosBresenham.add(new Point(x0 + x + maxX / 2, maxY - (y0 + y)));
                        puntosBresenham.add(new Point(x0 + y + maxX / 2, maxY - (y0 + x)));
                        puntosBresenham.add(new Point(x0 + y + maxX / 2, maxY - (y0 - x)));
                        puntosBresenham.add(new Point(x0 + x + maxX / 2, maxY - (y0 - y)));
                        puntosBresenham.add(new Point(x0 - x + maxX / 2, maxY - (y0 - y)));
                        puntosBresenham.add(new Point(x0 - y + maxX / 2, maxY - (y0 - x)));
                        puntosBresenham.add(new Point(x0 - y + maxX / 2, maxY - (y0 + x)));
                        puntosBresenham.add(new Point(x0 - x + maxX / 2, maxY - (y0 + y)));


                        if (d < 0) {
                            d = d + 4 * x + 6;
                        } else {
                            d = d + 4 * (x - y) + 10;
                            y--;
                        }
                        x++;
                    }


                    angulo++;
                    repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }


    void dibujarComparacionFinal(Graphics g, List<Point> puntos) {
        for (Point punto : puntos) {
            g.fillOval(punto.x, punto.y, 5, 5);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
         
          g.setFont(new Font("default", Font.BOLD, 30));
          g.drawString("ECUACIÓN", 80, 100);              
 	    g.drawString("BRESENHAM", maxX - 350, 100); 
        dibujarComparacionFinal(g, puntosOriginales);
        dibujarComparacionFinal(g, puntosBresenham);
    }


    public static void main(String[] args) {
        JFrame marco = new JFrame();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(1000, 1000);
        marco.add(new ComparacionFinal());
        marco.setVisible(true);
    }
}
