import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrilleGraphique extends JPanel {
    private static final long serialVersionUID = 1L;
    private int largeur, hauteur, taille_case;

    public static int iterateur1D = 0; // pour l'affichage des tab 1D dans la mm grille 

    public List<Point> casesAColorier;

    /**
     * Constructeur.
     * @param largeur La largeur (en nombre de cases) de la grille affichée.
     * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
     */
    public GrilleGraphique(int largeur, int hauteur, int taille_case) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.taille_case = taille_case;
        casesAColorier = new ArrayList<>();
    }

    @Override
    //Fonction d'affichage de la grille.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Point> casesCopy;
        synchronized (casesAColorier) {
            casesCopy = new ArrayList<>(casesAColorier);
        }
        for (Point fillCell : casesCopy) {
            int cellX = taille_case + (fillCell.x * taille_case);
            int cellY = taille_case + (fillCell.y * taille_case);
            g.setColor(Color.BLUE);
            g.fillRect(cellX, cellY, taille_case, taille_case);
        }

        g.setColor(Color.BLACK);
        g.drawRect(taille_case, taille_case, largeur * taille_case, hauteur * taille_case);

        for (int i = taille_case; i <= largeur * taille_case; i += taille_case) {
            g.drawLine(i, taille_case, i, hauteur * taille_case + taille_case);
        }

        for (int i = taille_case; i <= hauteur * taille_case; i += taille_case) {
            g.drawLine(taille_case, i, largeur * taille_case + taille_case, i);
        }
    }

    /**
     * Fonction permettant de colorier, en rouge, une case de la grille
     * @param x Abscisse de la case à colorier (entre 0 et largeur de grille - 1).
     * @param y Ordonnée de la case à colorier (entre 0 et hauteur de grille - 1).
     */
    public void colorierCase(int x, int y) {
        casesAColorier.add(new Point(x, y));
        repaint();
    }

    /**
     * Accesseur.
     * @return Renvoie la largeur de la grille
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Accesseur.
     * @return Renvoie la hauteur de la grille
     */
    public int getHauteur() {
        return hauteur;
    }

    public static void afficheur2D(int dim1, int dim2, TableauDND tab, GrilleGraphique grid) {
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                ArrayList<Integer> coord = new ArrayList<>();
                coord.add(i);
                coord.add(j);
                if (tab.getValue(coord) == 1) {
                    grid.colorierCase(j, i); // Color new state
                }
            }
        }
    }

    public static void afficheur1D(int dim2, TableauDND tab, GrilleGraphique grid) {  // pour le triangle de S.... par exemple;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < dim2; j++) {
                ArrayList<Integer> coord = new ArrayList<>();
                coord.add(i);
                coord.add(j);
                if (tab.getValue(coord) == 1) {
                    grid.colorierCase(j, iterateur1D); // Color new state
                }
            }
        }
        iterateur1D++;
    }

    private static List<Integer> parseMatlabSyntax(String matlabSyntax, int dimensions) {
        List<Integer> coords = new ArrayList<>(Collections.nCopies(dimensions, -1));
        matlabSyntax = matlabSyntax.replaceAll("[()\\s]", ""); // Enlever les parenthèses et les espaces
        String[] parts = matlabSyntax.split(",");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals(":")) {
                coords.set(i, Integer.parseInt(parts[i]));
            }
        }

        return coords;
    }

    public static void afficheurND(int dim1, int dim2, String matlabSyntax, TableauDND tab, GrilleGraphique grid) {
        List<Integer> coords = parseMatlabSyntax(matlabSyntax, tab.getTabBounds().size());
        afficherPlan2D(tab, coords, dim1, dim2, grid);
    }

    private static void afficherPlan2D(TableauDND tab, List<Integer> coords, int dim1, int dim2, GrilleGraphique grid) {
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                List<Integer> currentCoords = new ArrayList<>(coords);
                int indexDim1 = coords.indexOf(-1);
                int indexDim2 = coords.lastIndexOf(-1);

                if (indexDim1 != -1) {
                    currentCoords.set(indexDim1, i);
                }

                if (indexDim2 != -1) {
                    currentCoords.set(indexDim2, j);
                }

                if (tab.getValue(currentCoords) == 1) {
                    grid.colorierCase(j, i); // Colorier l'état
                }
            }
        }
    }
    
    
    public static boolean isMatlabSyntax(String matlabSyntax, int dimensions) {
        matlabSyntax = matlabSyntax.replaceAll("[()\\s]", "");

        if (!matlabSyntax.matches("[0-9,:]*")) {
            return false;
        }

        String[] parts = matlabSyntax.split(",");

        if (parts.length != dimensions) {
            return false;
        }

        int colonCount = 0;
        for (String part : parts) {
            if (part.equals(":")) {
                colonCount++;
            } else {
                try {
                    Integer.parseInt(part);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }

        // Vérifier que le nombre de ':' est égal - 2
        return colonCount == 2;
    }
}
