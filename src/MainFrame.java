import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String selectedFile;
    private int dimentionnalite;
    private int dim1;
    private int dim2;

    public MainFrame() {
        // Configurer le JFrame
        setTitle("Simulation d'Automate Cellulaire");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialiser le CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Ajouter les différentes pages
        cardPanel.add(new WelcomePage(this), "welcome");
        cardPanel.add(new MenuPage(this), "menu");
        cardPanel.add(new FileSelectionPage(this), "fileSelection");
        cardPanel.add(new SimulationPage(this), "simulation");

        add(cardPanel);
    }

    public void showCard(String cardName) {
        cardLayout.show(cardPanel, cardName);
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public int getDimentionnalite() {
        return dimentionnalite;
    }

    public void setDimentionnalite(int dimentionnalite) {
        this.dimentionnalite = dimentionnalite;
    }

    public int getDim1() {
        return dim1;
    }

    public void setDim1(int dim1) {
        this.dim1 = dim1;
    }

    public int getDim2() {
        return dim2;
    }

    public void setDim2(int dim2) {
        this.dim2 = dim2;
    }

    public void initializeSimulation(String filePath) {
        try {
            TraitementXML xml = new TraitementXML(filePath);
            setSelectedFile(filePath);
            setDimentionnalite(xml.getDimensionalite());
            List<Integer> dims = xml.getTaille();
            setDim1(dims.get(0));
            setDim2(dims.get(1));
            SimulationPage simulationPage = (SimulationPage) cardPanel.getComponent(3);
            simulationPage.configureForDimensionality(dimentionnalite, dim1, dim2);
            showCard("simulation");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la lecture du fichier XML.");
        }
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
