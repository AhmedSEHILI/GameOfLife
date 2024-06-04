import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class AutomateApp extends JFrame {

    public AutomateApp() {
        // Initialisation de la fenêtre principale
        setTitle("Automates Cellulaires");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Message de bienvenue et description
        JLabel welcomeLabel = new JLabel("<html><h1>Bienvenue!</h1><p>Ce programme vous permet de visualiser des automates cellulaires.<br>Vous pouvez soit télécharger un fichier pour exécuter des automates, soit choisir un fichier depuis le répertoire.</p></html>", SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Panel pour les boutons d'option
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Bouton pour l'option 1
        JButton option1Button = new JButton("Télécharger un fichier");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ajouterFichier(selectedFile);
                    JOptionPane.showMessageDialog(null, "Fichier sélectionné: " + selectedFile.getAbsolutePath());

                    // Traitement du fichier
                    TraitementXML xml=new TraitementXML(selectedFile.getAbsolutePath());

                    // new GridGUI(); //tjr 2 dimension
                }
            }
        });

        // Bouton pour l'option 2
        JButton option2Button = new JButton("Choisir un fichier depuis le répertoire");
        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre actuelle
                new XMLPannel();
                // showXmlFilesWindow(); // Affiche la nouvelle fenêtre
            }
        });

        buttonPanel.add(option1Button);
        buttonPanel.add(option2Button);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    public void ajouterFichier(File file){
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("Invalid file: " + file);
        }

        Path targetPath = Paths.get("C:\\Users\\added\\eclipse-workspace\\GameOfLife\\Files");
        try {
            Files.copy(file.toPath(), targetPath);
            JOptionPane.showMessageDialog(null, "Fichier ajoutée: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not copy file to target directory: " + targetPath);
        }
    }

}
