import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JPanel {
    private static final long serialVersionUID = 1L;
    private Color backgroundColor = Color.decode("#def6fa"); 

    public WelcomePage(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        JLabel welcomeLabel = new JLabel("Bienvenue à la Simulation d'Automate Cellulaire", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Continuer");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 18));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.showCard("menu");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(backgroundColor); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
