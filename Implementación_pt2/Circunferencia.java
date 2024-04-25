import java.awt.*;

public class Circunferencia {
    private int radio;
    private Color color;
    private String estiloLinea;
    private int grosorLinea;
    private int centerX;
    private int centerY;
    private Color colorRelleno;

    public Circunferencia(int radio, Color color) {
        this.radio = radio;
        this.color = color;
    }

    public Circunferencia(int radio, Color color, String linea, int grosorLinea) {
        this.radio = radio;
        this.color = color;
        this.estiloLinea = linea;
        this.grosorLinea = grosorLinea;
    }

    public Circunferencia(int radio, Color color, String linea, int grosorLinea, int centerX, int centerY) {
        this.radio = radio;
        this.color = color;
        this.estiloLinea = linea;
        this.grosorLinea = grosorLinea;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    public void trasladar(int deltaX, int deltaY) {
        this.centerX += deltaX;
        this.centerY += deltaY;
    }

    public void setLineThickness(int lineThickness) {
        this.grosorLinea = lineThickness;
    }

    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        if (estiloLinea.equals("Segmentado")) {
            float[] dashPattern = {10, 10};
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke());
        }
        g2d.setStroke(new BasicStroke(grosorLinea));
        g.drawOval(centerX - radio, centerY - radio, 2 * radio, 2 * radio);

        int x = 0, y = radio;
        int d = 3 - 2 * radio;

        drawCircle(g, x, y);

        while (y >= x) {
            x++;

            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            drawCircle(g, x, y);
        }

        // Llamada a la función rellenar() para rellenar el círculo
        if (colorRelleno != null) {
            rellenar(g);
        }
    }

    private void drawCircle(Graphics g, int x, int y) {
        if (estiloLinea.equals("Segmentado")) {
            if (x % 2 == 0) {
                drawPoints(g, x, y);
            }
        } else {
            drawPoints(g, x, y);
        }
    }

    private void drawPoints(Graphics g, int x, int y) {
        g.fillOval(centerX + x, centerY + y, 2, 2);
        g.fillOval(centerX + y, centerY + x, 2, 2);
        g.fillOval(centerX + y, centerY - x, 2, 2);
        g.fillOval(centerX + x, centerY - y, 2, 2);
        g.fillOval(centerX - x, centerY - y, 2, 2);
        g.fillOval(centerX - y, centerY - x, 2, 2);
        g.fillOval(centerX - y, centerY + x, 2, 2);
        g.fillOval(centerX - x, centerY + y, 2, 2);
    }

    public void rellenar(Graphics g) {
        g.setColor(colorRelleno);
        g.fillOval(centerX - radio, centerY - radio, 2 * radio, 2 * radio);
    }
}