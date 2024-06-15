import java.util.*;
import java.util.regex.*;

public class VoisinageExtractor{

    private String nom;
    private  List<List<Integer>> coordonnees;

    public VoisinageExtractor(String chaineVoisinage) { 
    	Pattern pattern = Pattern.compile("\\((-?\\d+(,\\s*-?\\d+)*)\\)");
        Matcher matcher = pattern.matcher(chaineVoisinage);
        	
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
    

}