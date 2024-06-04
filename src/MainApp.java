import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame {

    public MainApp() {
        // Initialisation de la fenêtre principale
        setTitle("Automates Cellulaires");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du panneau supérieur pour le titre
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Automates Cellulaires", SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Création du panneau pour les fichiers à droite
        JPanel filesPanel = new JPanel(new BorderLayout());
        filesPanel.setPreferredSize(new Dimension(200, 0)); // Largeur fixe

        // Ajout du panneau pour afficher les fichiers XML
        XMLPannel xmlPanel = new XMLPannel();
        filesPanel.add(xmlPanel, BorderLayout.CENTER);

        // Création du panneau pour la grille à gauche
        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(Color.DARK_GRAY);

        // Ajout des panneaux dans le panneau principal
        mainPanel.add(filesPanel, BorderLayout.EAST);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);
        pack(); // Ajuster la taille de la fenêtre en fonction du contenu
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.setVisible(true);
        });
    }
}
