import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicUI extends JFrame {
    private JTextField textField;
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

        JLabel title = new JLabel("CIRCUNFERENCIA", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

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

        // Reemplazar JColorChooser con JTextField para R, G, B
        JLabel redLabel = new JLabel("R:");
        redField = new JTextField(3);
        redField.setMaximumSize(redField.getPreferredSize());
        controlPanel.add(redLabel);
        controlPanel.add(redField);

        JLabel greenLabel = new JLabel("G:");
        greenField = new JTextField(3);
        greenField.setMaximumSize(greenField.getPreferredSize());
        controlPanel.add(greenLabel);
        controlPanel.add(greenField);

        JLabel blueLabel = new JLabel("B:");
        blueField = new JTextField(3);
        blueField.setMaximumSize(blueField.getPreferredSize());
        controlPanel.add(blueLabel);
        controlPanel.add(blueField);

        JButton pintar = new JButton("Pintar");
        pintar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int red = Integer.parseInt(redField.getText());
                int green = Integer.parseInt(greenField.getText());
                int blue = Integer.parseInt(blueField.getText());
                Color fillColor = new Color(red, green, blue);
                drawPanel.getCircunferencia().pintar(fillColor);
                drawPanel.repaint();
            }
        });
        controlPanel.add(pintar);

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
                    drawPanel.getCircunferencia().cambiarTamaño(sizeMultiplier);
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