package Fabian;
import javax.swing.*;
import java.awt.*;

public class CircunferenciaFrame extends JFrame {
    private int centroX, centroY, radio;

    public CircunferenciaFrame(int centroX, int centroY, int radio) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.radio = radio;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(centroX - radio, centroY - radio, radio * 2, radio * 2);
    }

    public static void main(String[] args) {
        new CircunferenciaFrame(250, 250, 100);
    }
}
