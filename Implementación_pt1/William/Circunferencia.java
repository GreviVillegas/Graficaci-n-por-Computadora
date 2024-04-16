package William;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Circunferencia extends JPanel {

    private int centroX, centroY, radio;

    public Circunferencia(int centroX, int centroY, int radio) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar 40 circunferencias una al lado de la otra
        for (int i = 0; i < 40; i++) {
            drawCirc(g2d, centroX + i, centroY, radio);
        }
    }

    private void drawCirc(Graphics2D g, int centroX, int centroY, int radio) {
        int x = 0;
        int y = radio;
        double decision = 5.0 / 4 - radio;

        while (y > x) {
            x++;
            if (decision < 0) {
                decision += 2 * x + 1;
            } else {
                y--;
                decision += 2 * (x - y) + 1;
            }
            g.drawLine(centroX + x, centroY + y, centroX + x, centroY + y);
            g.drawLine(centroX - x, centroY + y, centroX - x, centroY + y);
            g.drawLine(centroX + x, centroY - y, centroX + x, centroY - y);
            g.drawLine(centroX - x, centroY - y, centroX - x, centroY - y);
            g.drawLine(centroX + y, centroY + x, centroX + y, centroY + x);
            g.drawLine(centroX - y, centroY + x, centroX - y, centroY + x);
            g.drawLine(centroX + y, centroY - x, centroX + y, centroY - x);
            g.drawLine(centroX - y, centroY - x, centroX - y, centroY - x);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduzca las coordenadas del centro de la circunferencia:");
            System.out.print("Centro X: ");
            int centroX = sc.nextInt();
            System.out.print("Centro Y: ");
            int centroY = sc.nextInt();
            System.out.print("Radio: ");
            int radio = sc.nextInt();
            sc.close();

            JFrame frame = new JFrame("Circunferencias");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1800, 400);
            frame.getContentPane().add(new Circunferencia(centroX, centroY, radio));
            frame.setVisible(true);
        });
    }
}


