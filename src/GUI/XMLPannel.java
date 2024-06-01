package GUI;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class XMLPannel extends JFrame{

    public XMLPannel(){
        JFrame xmlFilesFrame = new JFrame("Fichiers XML disponibles");
        xmlFilesFrame.setSize(400, 300);
        xmlFilesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        xmlFilesFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Fichiers XML dans le répertoire:", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);

        File currentDirectory = new File("C:\\Users\\added\\eclipse-workspace\\GameOfLife\\Files"); // A changer en chemin realtif
        File[] files = currentDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
        
        if (files != null) {
            for (File file : files) {
                listModel.addElement(file.getName());
            }
        }

        panel.add(scrollPane, BorderLayout.CENTER);
        xmlFilesFrame.add(panel);
        xmlFilesFrame.setVisible(true);
    }
}
