import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class BasicUI extends JFrame {
    public BasicUI() {
        
        this.setTitle("Interfaz de usuario básica");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        
        JButton button = new JButton("Click aquí");
        this.add(button);
    }

    public static void main(String[] args) {
        // Crear una instancia de la interfaz de usuario y hacerla visible
        BasicUI ui = new BasicUI();
        ui.setVisible(true);
    }
}