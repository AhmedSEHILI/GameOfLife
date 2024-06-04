import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutomateApp app = new AutomateApp();
            app.setVisible(true);
        });
    }
}
