import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PaintProgram extends JFrame {
    public PaintProgram() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new PaintPanel());
    }

    public static void main(String[] args) {
        new PaintProgram().setVisible(true);
    }
}

class PaintPanel extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;

    public PaintPanel() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Aquí puedes cambiar la forma actual en función de la selección del usuario
                currentShape = new Line(e.getPoint(), e.getPoint());
                shapes.add(currentShape);
            }

            public void mouseReleased(MouseEvent e) {
                currentShape = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    currentShape.setSecondPoint(e.getPoint());
                    repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}

interface Shape {
    void draw(Graphics g);
    void setSecondPoint(Point point);
}

class Line implements Shape {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void draw(Graphics g) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public void setSecondPoint(Point point) {
        this.end = point;
    }
}