import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicUI extends JFrame {
    private JTextField textX1;
    private JTextField textY1;
    private JTextField textX2;
    private JTextField textY2;
    private JTextField textX3;
    private JTextField textY3;

    private int contador;
    private DrawPanel drawPanel;
    private JColorChooser colorChooser;

    private JComboBox<String> lineStyleComboBox;
    private JTextField deltaXc;
    private JTextField deltaYc;

    // Agregar campos de texto para R, G, B
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;

    public BasicUI() {
        this.setTitle("Interfaz de usuario básica");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        contador = 0;

        JLabel title = new JLabel("TRIANGULO", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(400, 400));

        JButton exportButton = new JButton("Exportar");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    drawPanel.printAll(g);
                    g.dispose();
        
                    File file = new File((System.getProperty("user.dir"))+"triangulo.png");
                    ImageIO.write(image, "png", file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(exportButton);
        //COLOR CIRCULO
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        controlPanel.add(colorChooser);

        JSlider lineThicknessSlider = new JSlider(1, 10, 1);
        lineThicknessSlider.setMajorTickSpacing(1);
        lineThicknessSlider.setPaintTicks(true);
        lineThicknessSlider.setPaintLabels(true);
        lineThicknessSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int lineThickness = (int)source.getValue();
                drawPanel.setLineThickness(lineThickness);
            }
        });
        controlPanel.add(new JLabel("Grosor de línea:"));
        controlPanel.add(lineThicknessSlider);

        DatoXY pos1, pos2, pos3;

        JLabel labelX1 = new JLabel("Ingrese el punto x1: ");
        textX1 = new JTextField(10);
        textX1.setMaximumSize(new Dimension(200, textX1.getPreferredSize().height));
        controlPanel.add(labelX1);
        controlPanel.add(textX1);

        JLabel labelY1 = new JLabel("Ingrese el punto y1: ");
        textY1 = new JTextField(10);
        textY1.setMaximumSize(new Dimension(200, textY1.getPreferredSize().height));
        controlPanel.add(labelY1);
        controlPanel.add(textY1);

        JLabel labelX2 = new JLabel("Ingrese el punto x2: ");
        textX2 = new JTextField(10);
        textX2.setMaximumSize(new Dimension(200, textX2.getPreferredSize().height));
        controlPanel.add(labelX2);
        controlPanel.add(textX2);

        JLabel labelY2 = new JLabel("Ingrese el punto y2: ");
        textY2 = new JTextField(10);
        textY2.setMaximumSize(new Dimension(200, textY2.getPreferredSize().height));
        controlPanel.add(labelY2);
        controlPanel.add(textY2);

        JLabel labelX3 = new JLabel("Ingrese el punto x3: ");
        textX3 = new JTextField(10);
        textX3.setMaximumSize(new Dimension(200, textX3.getPreferredSize().height));
        controlPanel.add(labelX3);
        controlPanel.add(textX3);

        JLabel labelY3 = new JLabel("Ingrese el punto y3: ");
        textY3 = new JTextField(10);
        textY3.setMaximumSize(new Dimension(200, textY3.getPreferredSize().height));
        controlPanel.add(labelY3);
        controlPanel.add(textY3);

        JButton button = new JButton("Iniciar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatoXY pos1 = new DatoXY(Integer.parseInt(textX1.getText()), Integer.parseInt(textY1.getText()));
                DatoXY pos2 = new DatoXY(Integer.parseInt(textX2.getText()), Integer.parseInt(textY2.getText()));
                DatoXY pos3 = new DatoXY(Integer.parseInt(textX3.getText()), Integer.parseInt(textY3.getText()));
                Color color;
                if(contador>0) color = colorChooser.getColor();
                else color = new Color(0,0,0,0);
                contador++;
                int grosorLinea = lineThicknessSlider.getValue();
                Triangulo triangulo = new Triangulo(pos1, pos2, pos3, color, drawPanel.getWidth(), drawPanel.getHeight());
                triangulo.setGrosorLinea(grosorLinea);
                drawPanel.setTriangulo(triangulo);
                drawPanel.repaint();
            }
        });
        controlPanel.add(button);

        JLabel deltaXLabel = new JLabel("Traslado en X: ");
        deltaXc = new JTextField(10);
        deltaXc.setMaximumSize(new Dimension(200, deltaXc.getPreferredSize().height));
        controlPanel.add(deltaXLabel);
        controlPanel.add(deltaXc);

        JLabel deltaYLabel = new JLabel("Traslado en Y: ");
        deltaYc = new JTextField(10);
        deltaYc.setMaximumSize(new Dimension(200, deltaYc.getPreferredSize().height));
        controlPanel.add(deltaYLabel);
        controlPanel.add(deltaYc);

        JButton moveButton = new JButton("Trasladar");
        moveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.getTriangulo().setTraslado(Integer.parseInt(deltaXc.getText()), 
                    Integer.parseInt(deltaYc.getText()));
                drawPanel.repaint();
            }
        });
        controlPanel.add(moveButton);

        // Añadir botón "Cambiar Tamaño"
        JButton sizeButton = new JButton("Cambiar Tamaño");
        sizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField sizeMultiplierField = new JTextField(5);
                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Multiplicador de tamaño:"));
                myPanel.add(sizeMultiplierField);
                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                       "Ingrese un multiplicador de tamaño", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    double sizeMultiplier = Double.parseDouble(sizeMultiplierField.getText());
                    drawPanel.getTriangulo().cambiarTamaño(sizeMultiplier);
                    drawPanel.repaint();
                }
            }
        });
        controlPanel.add(sizeButton);

        

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(400, 400));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(controlPanel);
        centerPanel.add(drawPanel);

        this.add(centerPanel, BorderLayout.CENTER);
    }   

    public static void main(String[] args) {
        BasicUI ui = new BasicUI();
        ui.pack();
        ui.setVisible(true);
    }
}