import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicUI extends JFrame {
    private JTextField textField;
    private DrawPanel drawPanel;
    private JColorChooser colorChooser;


    public BasicUI() {
        this.setTitle("Interfaz de usuario básica");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //setear el color
        colorChooser = new JColorChooser();

        this.add(colorChooser);

            // Crear un panel de vista previa personalizado
        JPanel previewPanel = new JPanel();
        previewPanel.setBackground(colorChooser.getColor());
        previewPanel.add(new JLabel("Muestra de Color", JLabel.CENTER));
        colorChooser.setPreviewPanel(previewPanel);
        this.add(colorChooser);

        JLabel title = new JLabel("CIRCUNFERENCIA", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title);

        JLabel label = new JLabel("Ingrese el radio: ");
        textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(200, textField.getPreferredSize().height));
        JButton button = new JButton("Iniciar");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int radio = Integer.parseInt(textField.getText());
                Color color = colorChooser.getColor();
                Circunferencia circunferencia = new Circunferencia(radio, color);
                
                drawPanel.setCircunferencia(circunferencia);
                drawPanel.repaint();
            }
        });

        this.add(label);
        this.add(textField);
        this.add(button);

        drawPanel = new DrawPanel();
        drawPanel.setPreferredSize(new Dimension(400, 400));
        this.add(drawPanel);
    }

    public static void main(String[] args) {
        BasicUI ui = new BasicUI();
        ui.pack();
        ui.setVisible(true);
    }
}