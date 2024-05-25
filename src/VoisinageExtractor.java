import java.util.*;
import java.util.regex.*;

public class VoisinageExtractor{

    private String nom;
    private  List<List<Integer>> coordonnees;

    public VoisinageExtractor(String chaineVoisinage) { 
    	//doivent tous avoir la mm longueur
        Pattern pattern = Pattern.compile("\\((-?\\d+(,\\s*-?\\d+)*)\\)");
        Matcher matcher = pattern.matcher(chaineVoisinage);
        	
        /// Exceptions : - gérer davantage la regex (bonne detection de format) (ex: les sous listes
        ///				 - Gk* vérifier si le pt (0,0) existe au pas
        List<List<Integer>> coordonnees = new ArrayList<List<Integer>>();
        while (matcher.find()) {
        	List<Integer> coord = new ArrayList<Integer>();
            String[] valeurs = matcher.group(1).split(",\\s*");
            for (String valeur : valeurs) {
                coord.add(Integer.parseInt(valeur));
            }
            coordonnees.add(coord);
        }

        this.nom = chaineVoisinage.substring(0, chaineVoisinage.indexOf("=")).strip();
        this.coordonnees = coordonnees;
    }

    public String getNom() {
        return nom;
    }

    public List<List<Integer>> getCoordonnees() {
        return coordonnees;
    }
    
/*
    public static void main(String[] args) {
    	VoisinageExtractor voisinage = new VoisinageExtractor("G2*={(1,1,1),(1,0,2),(1,7,6)}"); //gestion des exceptions de arraybounds
        System.out.println("Nom : " + voisinage.getNom());
        System.out.println("Coordonnées : " + voisinage.getCoordonnees());
    }
*/
}