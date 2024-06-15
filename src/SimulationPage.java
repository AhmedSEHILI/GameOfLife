import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimulationPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private GrilleGraphique grid;
    private TableauDND TDND;
    private traitementRegle traitement;
    private int dim1;
    private int dim2;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean step = new AtomicBoolean(false);
    private volatile int sleepTime = 100;
    private JSlider sleepTimeSlider;
    private JTextField coordInputField;
    private JLabel coordInputLabel;
    private JLabel infoLabel1, infoLabel2,infoLabel5, infoLabel3, infoLabel4;
    private MainFrame mainFrame;
    private JSplitPane mainSplitPane;
    private JSplitPane splitPane;
    
    private JButton startButton;
    private JButton stopButton;
    private JButton resumeButton;
    private JButton nextIterationButton;

    public SimulationPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        Color backgroundColor = Color.decode("#def6fa"); 

        dim1 = 20;
        dim2 = 20;
        grid = new GrilleGraphique(0, 0, 10);
        grid.setBackground(backgroundColor); 

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setBackground(backgroundColor); 

        JButton backButton = new JButton("Retour");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setBackground(backgroundColor); 
        backButtonPanel.add(backButton);
        buttonPanel.add(backButtonPanel);
        backButton.addActionListener(e -> {
            stopSimulation(); 
            resetSimulationPage(); 
            GrilleGraphique.iterateur1D = 0;
            mainFrame.showCard("menu");
        });

        coordInputLabel = new JLabel("Veuillez entrer les coordonnées du plan à afficher:");
        coordInputLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel coordInputLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coordInputLabelPanel.setBackground(backgroundColor); 
        coordInputLabelPanel.add(coordInputLabel);
        coordInputLabel.setVisible(false);
        buttonPanel.add(coordInputLabelPanel);

        coordInputField = new JTextField(20);
        coordInputField.setMaximumSize(new Dimension(200, 30)); 
        JPanel coordInputFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        coordInputFieldPanel.setBackground(backgroundColor); 
        coordInputFieldPanel.add(coordInputField);
        coordInputField.setVisible(false);
        buttonPanel.add(coordInputFieldPanel);

        JPanel sleepTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sleepTimePanel.setBackground(backgroundColor); 
        JLabel sleepTimeLabel = new JLabel("Vitesse d'évolution (ms):");
        sleepTimeSlider = new JSlider(0, 1000, sleepTime);
        sleepTimeSlider.setMajorTickSpacing(200);
        sleepTimeSlider.setMinorTickSpacing(50);
        sleepTimeSlider.setPaintTicks(true);
        sleepTimeSlider.setPaintLabels(true);
        sleepTimeSlider.addChangeListener(e -> setSleepTime());
        sleepTimePanel.add(sleepTimeLabel);
        sleepTimePanel.add(sleepTimeSlider);
        buttonPanel.add(sleepTimePanel);

        startButton = new JButton("Démarrer la Simulation");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel startButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        startButtonPanel.setBackground(backgroundColor); 
        startButtonPanel.add(startButton);
        buttonPanel.add(startButtonPanel);
        startButton.addActionListener(e -> startSimulation());

        stopButton = new JButton("Arrêter la Simulation");
        stopButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel stopButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stopButtonPanel.setBackground(backgroundColor);
        stopButtonPanel.add(stopButton);
        buttonPanel.add(stopButtonPanel);
        stopButton.addActionListener(e -> stopSimulation());

        resumeButton = new JButton("Reprendre la Simulation");
        resumeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel resumeButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resumeButtonPanel.setBackground(backgroundColor);
        resumeButtonPanel.add(resumeButton);
        buttonPanel.add(resumeButtonPanel);
        resumeButton.addActionListener(e -> resumeSimulation());

        nextIterationButton = new JButton("Prochaine Itération");
        nextIterationButton.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel nextIterationButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nextIterationButtonPanel.setBackground(backgroundColor); 
        nextIterationButtonPanel.add(nextIterationButton);
        buttonPanel.add(nextIterationButtonPanel);
        nextIterationButton.addActionListener(e -> nextIteration());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(backgroundColor); 

        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10), 
                BorderFactory.createTitledBorder("Informations")
        ));

        infoLabel1 = new JLabel("");
        infoLabel2 = new JLabel("");
        infoLabel3 = new JLabel("");
        infoLabel4 = new JLabel("");
        infoLabel5 = new JLabel("");

        infoPanel.add(infoLabel1);
        infoPanel.add(infoLabel2);
        infoPanel.add(infoLabel3);
        infoPanel.add(infoLabel4);
        infoPanel.add(infoLabel5);
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, grid);
        splitPane.setResizeWeight(0.05); 

        mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane, infoPanel);
        mainSplitPane.setResizeWeight(0.72); 

        add(mainSplitPane, BorderLayout.CENTER);

        stopButton.setEnabled(false);
        resumeButton.setEnabled(false);
        nextIterationButton.setEnabled(false);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setCoordInputVisibility(mainFrame.getDimentionnalite());
    }

    private void setSleepTime() {
        sleepTime = sleepTimeSlider.getValue();
    }

    private void setCoordInputVisibility(int dimentionnalite) {
        if (dimentionnalite >= 3) {
            coordInputLabel.setVisible(true);
            coordInputField.setVisible(true);
        } else {
            coordInputLabel.setVisible(false);
            coordInputField.setVisible(false);
        }
    }

    public void configureForDimensionality(int dimentionnalite, int dim1, int dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
        setCoordInputVisibility(dimentionnalite);
        if (dimentionnalite == 1) {
            grid = new GrilleGraphique(dim2, dim2, 10);
        } else {
            grid = new GrilleGraphique(dim1, dim2, 10);
        }
        splitPane.setRightComponent(grid); 
        revalidate();
        repaint();
    }

    public void resetSimulationPage() {
        this.dim1 = 20;
        this.dim2 = 20;
        grid = new GrilleGraphique(0, 0, 10);
        splitPane.setRightComponent(grid);
        infoLabel1.setText("");
        infoLabel2.setText("");
        infoLabel3.setText("");
        infoLabel4.setText("");
        infoLabel5.setText("");

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resumeButton.setEnabled(false);
        nextIterationButton.setEnabled(false);
    }

    private void startSimulation() {
        running.set(true);
        step.set(false);

        String selectedFile = mainFrame.getSelectedFile();
        int dimentionnalite = mainFrame.getDimentionnalite();
        
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un fichier XML dans la page précédente.");
            return;
        }

        setCoordInputVisibility(dimentionnalite);

        VoisinageExtractor v0 = new VoisinageExtractor("G0={(0,0)}");
        VoisinageExtractor v2 = new VoisinageExtractor("G2={(0,0),(0,-1),(0,1)}");
        VoisinageExtractor v3 = new VoisinageExtractor("G2*={(0,-1),(0,1)}");
        VoisinageExtractor v4 = new VoisinageExtractor("G4={(0,0),(-1,0),(1,0),(0,-1),(0,1)}");
        VoisinageExtractor v44 = new VoisinageExtractor("G4={(-1,0),(1,0),(0,-1),(0,1)}");
        VoisinageExtractor v8 = new VoisinageExtractor("G8={(0,0),(0,-1),(0,1),(1,1),(-1,1),(-1,1),(-1,-1)}");
        VoisinageExtractor v6 = new VoisinageExtractor("G6={(0,0,0),(0,-1,0),(0,1,0),(-1,0,0),(0,0,1),(1,0,0),(-1,0,0)}");


        TraitementXML xml = new TraitementXML(selectedFile);

        voisinageBDD.bddAdd(v0);
        voisinageBDD.bddAdd(v2);
        voisinageBDD.bddAdd(v3);
        voisinageBDD.bddAdd(v4);
        voisinageBDD.bddAdd(v44);
        voisinageBDD.bddAdd(v8);
        voisinageBDD.bddAdd(v6);


        List<Integer> dims = xml.getTaille();
        System.out.println(dims);

        System.out.println(xml.getVoisinages());

        TDND = new TableauDND(dims);
        xml.afficher();
        traitement = new traitementRegle(xml.getRegle(), TDND);

        xml.initTableau(TDND);

        infoLabel1.setText("Dimentionnalité: " + xml.getDimensionalite());
        infoLabel2.setText("Taille : " + xml.getTaille());
        infoLabel3.setText("Règle d'évolution: " + xml.getRegle());
        infoLabel4.setText("Voisinage : " + xml.getVoisinages());
        infoLabel4.setText("Etat initial : " + xml.getInitState());


        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        resumeButton.setEnabled(false);
        nextIterationButton.setEnabled(false);

        if(dimentionnalite == 1 || dimentionnalite == 2) {
            new Thread(() -> {
                try {
                    for (int i = 0; i < 1000; i++) {
                        synchronized (running) {
                            while (!running.get() && !step.get()) {
                                running.wait();
                            }
                            if (step.get()) {
                                step.set(false);
                            }

                            TableauDND TDNDsave = traitement.appliquerRegleTableauDND();
                            if(dimentionnalite == 1) {
                                GrilleGraphique.afficheur1D(dim2, TDND, grid);
                            } else {
                                GrilleGraphique.afficheur2D(dim1, dim2, TDND, grid);
                            }

                            if (TableauDND.comparerTabDND(TDND, TDNDsave)) break;

                            TDND = TableauDND.copyTableauDND(TDNDsave);
                            traitement.setTab(TDND);
                            Thread.sleep(sleepTime); 
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } else { 
            new Thread(() -> {
                try {
                    for (int i = 0; i < 1000; i++) {
                        synchronized (running) {
                            while (!running.get() && !step.get()) {
                                running.wait();
                            }
                            if (step.get()) {
                                step.set(false);
                            }

                            String coords = coordInputField.getText();
                            if (!GrilleGraphique.isMatlabSyntax(coords, dimentionnalite)) {
                                JOptionPane.showMessageDialog(this, "Veuillez entrer un plan valide\nVeuillez entrez un plan de type (0,:,:,1 ... ) ou le nombre de ':' est égal à deux");
                                startButton.setEnabled(true);
                                stopButton.setEnabled(false);
                                resumeButton.setEnabled(false);
                                nextIterationButton.setEnabled(false);
                                return;
                            }

                            TableauDND TDNDsave = traitement.appliquerRegleTableauDND();
                            GrilleGraphique.afficheurND(dim1, dim2, coords, TDND, grid);

                            if (TableauDND.comparerTabDND(TDND, TDNDsave)) break;

                            TDND = TableauDND.copyTableauDND(TDNDsave);
                            traitement.setTab(TDND);
                            Thread.sleep(sleepTime); 
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void stopSimulation() {
        running.set(false);
        stopButton.setEnabled(false);
        resumeButton.setEnabled(true);
        nextIterationButton.setEnabled(true);
    }

    private void resumeSimulation() {
        synchronized (running) {
            running.set(true);
            running.notify();
        }
        stopButton.setEnabled(true);
        resumeButton.setEnabled(false);
        nextIterationButton.setEnabled(false);
    }

    private void nextIteration() {
        synchronized (running) {
            running.set(false);
            step.set(true);
            running.notify();
        }
        stopButton.setEnabled(false);
        resumeButton.setEnabled(true);
        nextIterationButton.setEnabled(true);
    }
}
