import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Triangulo extends JPanel {
    private int x1, y1, x2, y2, x3, y3;
    private Color borderColor, fillColor;

    private JSlider redSlider, greenSlider, blueSlider;
    private JLabel rgbLabel;
    private JTextField x1Field, y1Field, x2Field, y2Field, x3Field, y3Field;

    public Triangulo(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;

        setLayout(new BorderLayout());

        // SLIDERS RGB
        JPanel rgbPanel = new JPanel(new GridLayout(0, 1));
        redSlider = createSlider("Red", 0, 255, borderColor.getRed());
        greenSlider = createSlider("Green", 0, 255, borderColor.getGreen());
        blueSlider = createSlider("Blue", 0, 255, borderColor.getBlue());
        rgbLabel = new JLabel("RGB: (" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")");
        rgbPanel.add(redSlider);
        rgbPanel.add(greenSlider);
        rgbPanel.add(blueSlider);
        rgbPanel.add(rgbLabel);
        add(rgbPanel, BorderLayout.WEST);

        // TEXT FIELDS
        JPanel pointsPanel = new JPanel(new GridLayout(0, 2));
        x1Field = new JTextField();
        y1Field = new JTextField();
        x2Field = new JTextField();
        y2Field = new JTextField();
        x3Field = new JTextField();
        y3Field = new JTextField();
        pointsPanel.add(new JLabel("x1: "));
        pointsPanel.add(x1Field);
        pointsPanel.add(new JLabel("y1: "));
        pointsPanel.add(y1Field);
        pointsPanel.add(new JLabel("x2: "));
        pointsPanel.add(x2Field);
        pointsPanel.add(new JLabel("y2: "));
        pointsPanel.add(y2Field);
        pointsPanel.add(new JLabel("x3: "));
        pointsPanel.add(x3Field);
        pointsPanel.add(new JLabel("y3: "));
        pointsPanel.add(y3Field);
        add(pointsPanel, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(e -> drawTriangle());
        buttonPanel.add(drawButton);
        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(e -> fillTriangle());
        buttonPanel.add(fillButton);
        add(buttonPanel, BorderLayout.SOUTH);

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
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores enteros para las coordenadas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //RELLENO TRIANGULO
    private void fillTriangle() {
    // Origen de coordenadas en el centro
    	int centerX = getWidth() / 2;
    	int centerY = getHeight() / 2;

    // Convertir las coordenadas de los vértices a las coordenadas de la ventana
    	int x1Window = centerX + x1;
    	int y1Window = centerY - y1;
    	int x2Window = centerX + x2;
    	int y2Window = centerY - y2;
    	int x3Window = centerX + x3;
    	int y3Window = centerY - y3;

    // Ordenar los vértices de arriba hacia abajo
    	if (y1Window > y2Window) {
    	    int tempX = x1Window;
    	    int tempY = y1Window;
    	    x1Window = x2Window;
    	    y1Window = y2Window;
    	    x2Window = tempX;
    	    y2Window = tempY;
    	}
    	if (y1Window > y3Window) {
    	    int tempX = x1Window;
    	    int tempY = y1Window;
    	    x1Window = x3Window;
    	    y1Window = y3Window;
    	    x3Window = tempX;
    	    y3Window = tempY;
    	}
    	if (y2Window > y3Window) {
    	    int tempX = x2Window;
    	    int tempY = y2Window;
    	    x2Window = x3Window;
    	    y2Window = y3Window;
    	    x3Window = tempX;
    	    y3Window = tempY;
    	}

    // Inicializar los bordes izquierdo y derecho de cada línea del triángulo
    	int[] leftX = new int[getHeight()];
    	int[] rightX = new int[getHeight()];
    	for (int i = 0; i < getHeight(); i++) {
    	    leftX[i] = getWidth();
    	    rightX[i] = 0;
    	}

    // Calcular los bordes izquierdo y derecho de cada línea del triángulo
    	calculateEdge(x1Window, y1Window, x2Window, y2Window, leftX, rightX);
    	calculateEdge(x2Window, y2Window, x3Window, y3Window, leftX, rightX);
    	calculateEdge(x3Window, y3Window, x1Window, y1Window, leftX, rightX);

    // Rellenar el triángulo con el color de relleno actual
    	Graphics g = getGraphics();
    	for (int y = 0; y < getHeight(); y++) {
    	    for (int x = leftX[y]; x <= rightX[y]; x++) {
    	        g.fillRect(x, y, 1, 1);
    	    }
    	}
	}

	private void calculateEdge(int x1, int y1, int x2, int y2, int[] leftX, int[] rightX) {
    		int dx = x2 - x1;
    		int dy = y2 - y1;
    		int sign = dy < 0 ? -1 : 1;
	
    		int x = x1;
    		int y = y1;
    		int yi = sign;

    		if (dx == 0) {
        // Línea vertical
        	for (; y != y2; y += yi) {
        	    leftX[y] = Math.min(leftX[y], x);
        	    rightX[y] = Math.max(rightX[y], x);
        	}
    		} else if (Math.abs(dy) < Math.abs(dx)) {
        // Pendiente < 1
        	int D = 2 * Math.abs(dy) - Math.abs(dx);
        	int incrE = 2 * Math.abs(dy);
        	int incrNE = 2 * (Math.abs(dy) - Math.abs(dx));
        	while (x != x2) {
            		leftX[y] = Math.min(leftX[y], x);
            		rightX[y] = Math.max(rightX[y], x);
            		x++;
            		if (D < 0) {
                		D += incrE;
            		} else {
                		D += incrNE;
                		y += yi;
            		}
        	}
    		} else {
        // Pendiente >= 1
        	int D = 2 * Math.abs(dx) - Math.abs(dy);
        	int incrE = 2 * Math.abs(dx);
        	int incrNE = 2 * (Math.abs(dx) - Math.abs(dy));
        	while (y != y2) {
        	    leftX[y] = Math.min(leftX[y], x);
        	    rightX[y] = Math.max(rightX[y], x);
        	    y += yi;
        	    if (D < 0) {
        	        D += incrE;
        	    } else {
        	        D += incrNE;
        	        x++;
        	    }
        	}
    		}
	}

    //---

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

    //**********************************************************
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(borderColor);

        // Origen de coordenadas en el centro
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Dibujar el triángulo con el color de borde especificado
        drawLineBresenham(g, centerX + x1, centerY - y1, centerX + x2, centerY - y2);
        drawLineBresenham(g, centerX + x2, centerY - y2, centerX + x3, centerY - y3);
        drawLineBresenham(g, centerX + x3, centerY - y3, centerX + x1, centerY - y1);
    }

    public static void main(String[] args) {
        // Color de borde y color de relleno por defecto
        Color borderColor = Color.RED;
        Color fillColor = Color.WHITE;

        // Crear y configurar la ventana
        JFrame frame = new JFrame("Bresenham Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        // Agregar el panel al marco
        Triangulo panel = new Triangulo(borderColor, fillColor);
        frame.add(panel);

        // Mostrar el marco
        frame.setVisible(true);
    }
}

