import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooserPanel extends JPanel {

    private JSlider redSlider, greenSlider, blueSlider;
    private JSlider hueSlider, saturationSlider, brightnessSlider;
    private JLabel rgbLabel, hslLabel;

    public ColorChooserPanel() {
        setLayout(new GridLayout(0, 1));

        // Sliders para RGB
        redSlider = createSlider("Red", 0, 255, 0);
        greenSlider = createSlider("Green", 0, 255, 0);
        blueSlider = createSlider("Blue", 0, 255, 0);

        // Sliders para HSL
        hueSlider = createSlider("Hue", 0, 360, 0);
        saturationSlider = createSlider("Saturation", 0, 100, 0);
        brightnessSlider = createSlider("Brightness", 0, 100, 0);

        // Etiquetas para mostrar los valores de RGB y HSL
        rgbLabel = new JLabel("RGB: (0, 0, 0)");
        hslLabel = new JLabel("HSL: (0, 0, 0)");

        add(redSlider);
        add(greenSlider);
        add(blueSlider);
        add(rgbLabel);

        add(hueSlider);
        add(saturationSlider);
        add(brightnessSlider);
        add(hslLabel);

        // Agregar oyentes de cambio para actualizar las etiquetas de color
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                rgbLabel.setText("RGB: (" + red + ", " + green + ", " + blue + ")");

                float hue = hueSlider.getValue() / 360.0f;
                float saturation = saturationSlider.getValue() / 100.0f;
                float brightness = brightnessSlider.getValue() / 100.0f;
                hslLabel.setText("HSL: (" + hue + ", " + saturation + ", " + brightness + ")");
            }
        };

        redSlider.addChangeListener(listener);
        greenSlider.addChangeListener(listener);
        blueSlider.addChangeListener(listener);
        hueSlider.addChangeListener(listener);
        saturationSlider.addChangeListener(listener);
        brightnessSlider.addChangeListener(listener);
    }

    private JSlider createSlider(String label, int min, int max, int initial) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing((max - min) / 4);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.getContentPane().add(new ColorChooserPanel());
        frame.setVisible(true);
    }
}
