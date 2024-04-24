import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicUI extends JFrame {
    private JTextField textField;
    private DrawPanel drawPanel;

    public BasicUI() {
        this.setTitle("Interfaz de usuario básica");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel title = new JLabel("CIRCUNFERENCIA");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title);

        JLabel label = new JLabel("Ingrese el radio: ");
        textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(200, textField.getPreferredSize().height));
        JButton button = new JButton("Iniciar");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int radio = Integer.parseInt(textField.getText());
                Circunferencia circunferencia = new Circunferencia(radio);
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