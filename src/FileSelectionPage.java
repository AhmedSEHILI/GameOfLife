import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileSelectionPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private MainFrame mainFrame;

    public FileSelectionPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        Color backgroundColor = Color.decode("#def6fa"); 

        JLabel label = new JLabel("Les fichiers disponibles sont :", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setOpaque(true); 
        label.setBackground(backgroundColor);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(label, BorderLayout.NORTH);

        JPanel filesPanel = new JPanel();
        filesPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
        filesPanel.setBackground(backgroundColor);

        JScrollPane scrollPane = new JScrollPane(filesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(backgroundColor); 
        add(scrollPane, BorderLayout.CENTER);

        File folder = new File("Files");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                try {
                    TraitementXML xml = new TraitementXML(file.getPath());
                    String info1 = "Dimentionnalité: " + xml.getDimensionalite();
                    String info2 = "Taille : " + xml.getTaille();
                    String info3 = "Règle d'évolution: " + xml.getRegle();
                    String info4 = "Voisinage : " + xml.getVoisinages();
                    String info5 = "Etat initial : " + xml.getInitState();

                    
                    JPanel filePanel = new JPanel();
                    filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
                    filePanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(20, 20, 20, 20), 
                        BorderFactory.createTitledBorder(file.getName())
                    ));
                    filePanel.setBackground(Color.WHITE); 

                    JLabel infoLabel1 = new JLabel(info1);
                    JLabel infoLabel2 = new JLabel(info2);
                    JLabel infoLabel3 = new JLabel(info3);
                    JLabel infoLabel4 = new JLabel(info4);
                    JLabel infoLabel5 = new JLabel(info5);


                    filePanel.add(infoLabel1);
                    filePanel.add(infoLabel2);
                    filePanel.add(infoLabel3);
                    filePanel.add(infoLabel4);
                    filePanel.add(infoLabel5);


                    int maxWidth = Math.max(infoLabel1.getPreferredSize().width, 
                                            Math.max(infoLabel2.getPreferredSize().width, 
                                                     Math.max(infoLabel3.getPreferredSize().width, 
                                                              infoLabel4.getPreferredSize().width)));
                    maxWidth += 20; 

                    filePanel.setPreferredSize(new Dimension(maxWidth, 200));

                    filePanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            mainFrame.initializeSimulation(file.getPath());
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            filePanel.setBackground(Color.LIGHT_GRAY); 
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            filePanel.setBackground(Color.WHITE);
                        }
                    });

                    filesPanel.add(filePanel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

