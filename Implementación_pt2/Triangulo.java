import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Triangulo extends JPanel{
	private int x1,y1,x2,y2,x3,y3;
	private Color borderColor, fillColor;
	
	private JSlider redSlider, greenSlider, blueSlider;
	private JLabel rgbLabel;
	
	public Triangulo(int x1,int y1,int x2,int y2,int x3,int y3, Color borderColor, Color fillColor){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		this.borderColor = borderColor;
		this.fillColor	 = fillColor;
		
		setLayout(new BorderLayout());
		//SLIDERS
		JPanel rgbPanel = new JPanel(new GridLayout(0,1));
		redSlider = createSlider("Red",0,255,borderColor.getRed());
		greenSlider = createSlider("Green",0,255,borderColor.getGreen());
		blueSlider = createSlider("Blue",0,255,borderColor.getBlue());
		
		

		
		rgbLabel = new JLabel("RGB: (" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")");
        	
        	
        	
		rgbPanel.add(redSlider);
		rgbPanel.add(greenSlider);
		rgbPanel.add(blueSlider);
		rgbPanel.add(rgbLabel);
		
		add(rgbPanel, BorderLayout.WEST);
		//---
		
		//Escuchador para refres Sliders
		
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
		
		//---
		
		
	}
	
	private JSlider createSlider(String label, int min, int max, int init){
		JSlider slider = new JSlider(min, max, init);
		slider.setMajorTickSpacing((max - min)/4);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBorder(BorderFactory.createTitledBorder(label));
		return slider;
	}
	
	private void refreshColor(){
		borderColor = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
        	rgbLabel.setText("RGB: (" + borderColor.getRed() + ", " + borderColor.getGreen() + ", " + borderColor.getBlue() + ")");
        	repaint();
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

    //**********************************************************
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(borderColor); // COLOR¡¡¡¡
	
	int centerX = getWidth()/2;
	int centerY = getHeight()/2;
	
        
        drawLineBresenham(g, centerX + x1, centerY - y1, centerX + x2, centerY - y2);
        drawLineBresenham(g, centerX + x2, centerY - y2, centerX + x3, centerY - y3);
        drawLineBresenham(g, centerX + x3, centerY - y3, centerX + x1, centerY - y1);
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese las coordenadas de los tres puntos del triángulo (x y):");
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int x3 = scanner.nextInt();
        int y3 = scanner.nextInt();
	
	Color borderColor = Color.BLACK;
	Color fillColor = Color.WHITE;
	

        // Crear y configurar la ventana
        JFrame frame = new JFrame("Bresenham Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Agregar el panel al marco
        Triangulo panel = new Triangulo(x1, y1, x2, y2, x3, y3, borderColor, fillColor);
        
        frame.add(panel);

        // Mostrar el marco
        frame.setVisible(true);
    }
	
}
