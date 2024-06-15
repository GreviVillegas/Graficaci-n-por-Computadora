import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BasicUI extends JFrame {
    private JTextField textField;
    private DrawPanel drawPanel;
    private JColorChooser colorChooser;

    private JComboBox<String> lineStyleComboBox;

    public BasicUI() {
        this.setTitle("Interfaz de usuario no tan básica");
        this.setSize(600, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("FRACTALES", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title, BorderLayout.NORTH);

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(500, 650));
        drawPanel.setBackground(Color.BLACK);

        JButton exportButton = new JButton("Exportar");
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = image.createGraphics();
                    drawPanel.printAll(g);
                    g.dispose();
        
                    File file = new File((System.getProperty("user.dir"))+"fractales.png");
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
        //Color
        colorChooser = new JColorChooser();
        colorChooser.setColor(Color.cyan);;
        colorChooser.setPreviewPanel(new JPanel());
        controlPanel.add(colorChooser);

        lineStyleComboBox = new JComboBox<>(new String[]{"Segmentado", "Continuo"});
        controlPanel.add(lineStyleComboBox);

        JSlider lineThicknessSlider = new JSlider(0, 10, 0);
        lineThicknessSlider.setMajorTickSpacing(1);
        lineThicknessSlider.setPaintTicks(true);
        lineThicknessSlider.setPaintLabels(true);
        controlPanel.add(new JLabel("Nivel:"));
        controlPanel.add(lineThicknessSlider);

        JLabel label = new JLabel("Ingrese un tamaño: ");
        label.setHorizontalAlignment(10);
        textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(300, textField.getPreferredSize().height));
        controlPanel.add(label);
        controlPanel.add(textField);

        JButton button = new JButton("Iniciar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = colorChooser.getColor();
                int tamaño = 0;
                try{
                    tamaño = Integer.parseInt(textField.getText());
                }catch(Exception exception){}
                Triangulo triangulo = new Triangulo(tamaño);
                triangulo.setFractal(lineThicknessSlider.getValue());
                triangulo.setColor(color);
                drawPanel.setTriangulo(triangulo);
                drawPanel.repaint();
            }
        });
        controlPanel.add(button);
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