import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicUI extends JFrame {
    private JTextField textField;
    private DrawPanel drawPanel;
    private JColorChooser colorChooser;
    private JColorChooser colorChooserFill;

    private JComboBox<String> lineStyleComboBox;
    private JTextField deltaXc;
    private JTextField deltaYc;

    public BasicUI() {
        this.setTitle("Interfaz de usuario básica");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("CIRCUNFERENCIA", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

<<<<<<< Updated upstream
=======
        JButton exportButton = new JButton("Exportar");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    drawPanel.printAll(g);
                    g.dispose();
        
                    File file = new File((System.getProperty("user.dir"))+"imagen.png");
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
>>>>>>> Stashed changes
        //COLOR CIRCULO
        colorChooser = new JColorChooser();
        JPanel previewPanel = new JPanel();
        previewPanel.setBackground(colorChooser.getColor());
        previewPanel.add(new JLabel("", JLabel.CENTER));
        colorChooser.setPreviewPanel(previewPanel);
        controlPanel.add(colorChooser);


        
        lineStyleComboBox = new JComboBox<>(new String[]{"Segmentado", "Continuo"});
        controlPanel.add(lineStyleComboBox);

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

        JLabel label = new JLabel("Ingrese el radio: ");
        textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(200, textField.getPreferredSize().height));
        controlPanel.add(label);
        controlPanel.add(textField);

        JButton button = new JButton("Iniciar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int radio = Integer.parseInt(textField.getText());
                Color color = colorChooser.getColor();
                String estiloLinea = (String) lineStyleComboBox.getSelectedItem();
                int grosorLinea = lineThicknessSlider.getValue();
                Circunferencia circunferencia = new Circunferencia(radio, color, estiloLinea, grosorLinea, drawPanel.getWidth() / 2, drawPanel.getHeight() / 2);
                drawPanel.setCircunferencia(circunferencia);
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
                int deltaX = Integer.parseInt(deltaXc.getText());
                int deltaY = Integer.parseInt(deltaYc.getText());
                drawPanel.getCircunferencia().trasladar(deltaX, deltaY);
                drawPanel.repaint();
            }
        });
        controlPanel.add(moveButton);



        JLabel relleno = new JLabel("Relleno de la circunferencia:");
        controlPanel.add(relleno);

        colorChooserFill = new JColorChooser();
        JPanel previewPanelFill = new JPanel();
        previewPanelFill.setBackground(colorChooserFill.getColor());
        colorChooserFill.setPreviewPanel(previewPanelFill);
        controlPanel.add(colorChooserFill);

        JButton pintar = new JButton("Pintar");
        pintar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawPanel.getCircunferencia().pintar(colorChooserFill.getColor());;
                drawPanel.repaint();
            }
        });
        controlPanel.add(pintar);


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