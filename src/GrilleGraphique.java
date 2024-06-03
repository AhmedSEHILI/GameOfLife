
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Merci à StackOverflow pour sa précieuse contribution !


public class GrilleGraphique extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int largeur, hauteur, taille_case;
	
	public static int iterateur1D = 0; // pour l'affichage des tab 1D dans la mm grille 
	
	public List<Point> casesAColorier;

	/**
	 * Constructeur.
	 * @param largeur La largeur (en nombre de cases) de la grille affichée.
	 * @param hauteur La hauteur (en nombre de cases) de la grille affichée.
	 */
	public GrilleGraphique(int largeur, int hauteur, int taille_case) 
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.taille_case = taille_case;
		casesAColorier = new ArrayList<>();

		JFrame window = new JFrame();
		window.setSize(largeur*taille_case+200, hauteur*taille_case+200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
		window.setVisible(true);
	}

	@Override
	//Fonction d'affichage de la grille.
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		for (Point fillCell : casesAColorier) 
		{
			int cellX = taille_case + (fillCell.x * taille_case);
			int cellY = taille_case + (fillCell.y * taille_case);
			g.setColor(Color.BLUE);
			g.fillRect(cellX, cellY, taille_case, taille_case);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(taille_case, taille_case, largeur*taille_case, hauteur*taille_case);

		for (int i = taille_case; i <= largeur*taille_case; i += taille_case) {
			g.drawLine(i, taille_case, i, hauteur*taille_case+taille_case);
		}

		for (int i = taille_case; i <= hauteur*taille_case; i += taille_case) {
			g.drawLine(taille_case, i, largeur*taille_case+taille_case, i);
		}
	}

	/**
	 * Fonction permettant de colorier, en rouge, une case de la grille
	 * @param x Abscisse de la case à colorier (entre 0 et largeur de grille - 1).
	 * @param y Ordonnée de la case à colorier (entre 0 et hauteur de grille - 1).
	 */
	public void colorierCase(int x, int y) 
	{
		casesAColorier.add(new Point(x, y));
		repaint();
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la largeur de la grille
	 */
	public int getLargeur()
	{
		return largeur;
	}
	
	/**
	 * Accesseur.
	 * @return Renvoie la hauteur de la grille
	 */
	public int getHauteur()
	{
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
        
        iterateur1D ++;
	}
}
