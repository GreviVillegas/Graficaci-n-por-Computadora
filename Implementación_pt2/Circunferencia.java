import java.awt.*;

public class Circunferencia {
    private int radio;
    private Color color;

    public Circunferencia(int radio, Color color) {
        this.radio = radio;
        this.color = color;
    }

    public void dibujar(Graphics g, int centerX, int centerY) {
        g.setColor(color);
        int x = 0, y = radio;
        int d = 3 - 2 * radio;

        drawCircle(g, x, y, centerX, centerY);

        while (y >= x) {
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawCircle(g, x, y, centerX, centerY);
        }
    }

    private void drawCircle(Graphics g, int x, int y, int centerX, int centerY) {
        g.fillOval(centerX + x, centerY + y, 2, 2);
        g.fillOval(centerX + y, centerY + x, 2, 2);
        g.fillOval(centerX + y, centerY - x, 2, 2);
        g.fillOval(centerX + x, centerY - y, 2, 2);
        g.fillOval(centerX - x, centerY - y, 2, 2);
        g.fillOval(centerX - y, centerY - x, 2, 2);
        g.fillOval(centerX - y, centerY + x, 2, 2);
        g.fillOval(centerX - x, centerY + y, 2, 2);
    }
}