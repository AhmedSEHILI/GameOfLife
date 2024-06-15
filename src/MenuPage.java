import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private Color backgroundColor = Color.decode("#def6fa");

    public MenuPage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(backgroundColor); 

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(backgroundColor); 
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        JLabel titleLabel = new JLabel("C'est quoi un automate cellulaire ?", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);

        JTextArea descriptionArea = new JTextArea(20, 40); 
        descriptionArea.setText("Un automate cellulaire est un modèle mathématique pour un système dynamique "
                + "qui évolue dans le temps en suivant un ensemble de règles définies localement. Les automates cellulaires sont utilisés dans divers domaines "
                + "tels que la biologie, l'informatique, la physique et les sciences sociales pour modéliser des phénomènes complexes.\n\n"
                + "Les automates cellulaires sont composés de cellules disposées sur une grille. Chaque cellule peut prendre un état parmi un ensemble fini d'états. "
                + "L'état de chaque cellule évolue à chaque étape de temps en fonction de l'état de ses cellules voisines, selon des règles prédéfinies. "
                + "Ces règles peuvent être simples ou complexes et peuvent conduire à des comportements émergents fascinants.\n\n"
                + "L'un des exemples les plus célèbres d'automate cellulaire est le Jeu de la vie, inventé par le mathématicien John Conway. "
                + "Dans ce jeu, chaque cellule sur une grille carrée peut être vivante ou morte. À chaque étape de temps, l'état des cellules évolue "
                + "selon des règles simples basées sur le nombre de voisins vivants. Malgré la simplicité des règles, le Jeu de la vie peut produire des motifs "
                + "extraordinairement complexes et imprévisibles.\n\n"
                + "Les automates cellulaires ont des applications pratiques dans de nombreux domaines, notamment la modélisation de la croissance des populations, "
                + "la diffusion des maladies, la dynamique des fluides et la génération de motifs artistiques. Ils sont également utilisés dans les algorithmes de "
                + "calcul parallèle et les simulations de systèmes naturels.");
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(backgroundColor);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
        centerPanel.add(descriptionArea);

        JButton simulateButton = new JButton("Simuler un Automate Cellulaire");
        simulateButton.setFont(new Font("Arial", Font.PLAIN, 18));
        simulateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showCard("fileSelection");
            }
        });
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(simulateButton);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setBackground(backgroundColor); 
        containerPanel.add(centerPanel);

        add(containerPanel, BorderLayout.CENTER);
    }
}
