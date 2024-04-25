import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Triangulo extends JPanel {
    private int x1, y1, x2, y2, x3, y3;
    private Color borderColor, fillColor;

    private JSlider redSlider, greenSlider, blueSlider, hueSlider, saturationSlider, luminositySlider;
    private JLabel rgbLabel, hslLabel;
    private JTextField x1Field, y1Field, x2Field, y2Field, x3Field, y3Field;

    public Triangulo(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;

        setLayout(new BorderLayout());

        // SLIDERS RGB
        JPanel rgbPanel = createRGBPanel();
        add(rgbPanel, BorderLayout.WEST);

        // SLIDERS HSL
        JPanel hslPanel = createHSLPanel();
        hslPanel.setVisible(false);
        add(hslPanel, BorderLayout.EAST);

        // TEXT FIELDS
        JPanel pointsPanel = createPointsPanel();
        add(pointsPanel, BorderLayout.NORTH);
	
	// BUTTON
        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> drawTriangle());
       	add(drawButton, BorderLayout.SOUTH);
	
        // TOGGLE BUTTON
        JButton toggleButton = new JButton("Toggle Controls");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rgbPanel.setVisible(!rgbPanel.isVisible());
                hslPanel.setVisible(!hslPanel.isVisible());
                revalidate();
                repaint();
            }
        });
        //add(toggleButton, BorderLayout.SOUTH);

        // Slider listeners
        redSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColor();
            }
        });

        greenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColor();
            }
        });

        blueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColor();
            }
        });

        hueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColorFromHSL();
            }
        });

        saturationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColorFromHSL();
            }
        });

        luminositySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refreshColorFromHSL();
            }
        });
    }

    private JPanel createRGBPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        redSlider = createSlider("Red", 0, 255, borderColor.getRed());
        greenSlider = createSlider("Green", 0, 255, borderColor.getGreen());
        blueSlider = createSlider("Blue", 0, 255, borderColor.getBlue());
        rgbLabel = new JLabel("RGB: (" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")");
        panel.add(redSlider);
        panel.add(greenSlider);
        panel.add(blueSlider);
        panel.add(rgbLabel);
        return panel;
    }

    private JPanel createHSLPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        hueSlider = createSlider("Hue", 0, 360, getHue(borderColor));
        saturationSlider = createSlider("Saturation", 0, 100, getSaturation(borderColor));
        luminositySlider = createSlider("Luminosity", 0, 100, getLuminosity(borderColor));
        hslLabel = new JLabel("HSL: (" + getHue(borderColor) + ", " + getSaturation(borderColor) + ", " + getLuminosity(borderColor) + ")");
        panel.add(hueSlider);
        panel.add(saturationSlider);
        panel.add(luminositySlider);
        panel.add(hslLabel);
        return panel;
    }

    private JPanel createPointsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        x1Field = new JTextField();
        y1Field = new JTextField();
        x2Field = new JTextField();
        y2Field = new JTextField();
        x3Field = new JTextField();
        y3Field = new JTextField();
        panel.add(new JLabel("x1: "));
        panel.add(x1Field);
        panel.add(new JLabel("y1: "));
        panel.add(y1Field);
        panel.add(new JLabel("x2: "));
        panel.add(x2Field);
        panel.add(new JLabel("y2: "));
        panel.add(y2Field);
        panel.add(new JLabel("x3: "));
        panel.add(x3Field);
        panel.add(new JLabel("y3: "));
        panel.add(y3Field);
        return panel;
    }

    private JSlider createSlider(String label, int min, int max, int init) {
        JSlider slider = new JSlider(min, max, init);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }

    private void refreshColor() {
        borderColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
        rgbLabel.setText("RGB: (" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")");
        repaint();
    }

    private void refreshColorFromHSL() {
        float[] hsl = new float[3];
        hsl[0] = (float) hueSlider.getValue();
        hsl[1] = (float) saturationSlider.getValue() / 100;
        hsl[2] = (float) luminositySlider.getValue() / 100;
        borderColor = hslToRgb(hsl);
        hslLabel.setText("HSL: (" + (int) hsl[0] + ", " + (int) (hsl[1] * 100) + ", " + (int) (hsl[2] * 100) + ")");
        repaint();
    }

    private Color hslToRgb(float[] hsl) {
        float hue = hsl[0] / 360;
        float saturation = hsl[1];
        float luminosity = hsl[2];
        float q = luminosity < 0.5 ? luminosity * (1 + saturation) : (luminosity + saturation) - (saturation * luminosity);
        float p = 2 * luminosity - q;
        float r = hueToRgb(p, q, hue + (1.0f / 3));
        float g = hueToRgb(p, q, hue);
        float b = hueToRgb(p, q, hue - (1.0f / 3));
        return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255));
    }

    private float hueToRgb(float p, float q, float t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1.0f / 6) return p + (q - p) * 6 * t;
        if (t < 1.0f / 2) return q;
        if (t < 2.0f / 3) return p + (q - p) * (2.0f / 3 - t) * 6;
        return p;
    }

    private int getHue(Color color) {
        float[] hsl = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return (int) (hsl[0] * 360);
    }

    private int getSaturation(Color color) {
        float[] hsl = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return (int) (hsl[1] * 100);
    }

    private int getLuminosity(Color color) {
        float[] hsl = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return (int) (hsl[2] * 100);
    }

    private void drawTriangle() {
        try {
            x1 = Integer.parseInt(x1Field.getText());
            y1 = Integer.parseInt(y1Field.getText());
            x2 = Integer.parseInt(x2Field.getText());
            y2 = Integer.parseInt(y2Field.getText());
            x3 = Integer.parseInt(x3Field.getText());
            y3 = Integer.parseInt(y3Field.getText());
            repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores enteros de coordenadas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void drawLineBresenham(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int x = x1;
        int y = y1;

        while (true) {
            g.fillRect(x, y, 1, 1);

            if (x == x2 && y == y2) break;

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(borderColor);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        drawLineBresenham(g, centerX + x1, centerY - y1, centerX + x2, centerY - y2);
        drawLineBresenham(g, centerX + x2, centerY - y2, centerX + x3, centerY - y3);
        drawLineBresenham(g, centerX + x3, centerY - y3, centerX + x1, centerY - y1);
    }

    public static void main(String[] args) {
        Color borderColor = Color.RED;
        Color fillColor = Color.WHITE;

        JFrame frame = new JFrame("Bresenham Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        Triangulo panel = new Triangulo(borderColor, fillColor);

        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

