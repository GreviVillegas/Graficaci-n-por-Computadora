import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class BasicUI extends JFrame {
    public BasicUI() {
        // Configurar el comportamiento de la ventana
        this.setTitle("Interfaz de usuario básica");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        // Crear y agregar un botón a la ventana
        JButton button = new JButton("Click me");
        this.add(button);
    }

    public static void main(String[] args) {
        // Crear una instancia de la interfaz de usuario y hacerla visible
        BasicUI ui = new BasicUI();
        ui.setVisible(true);
    }
}