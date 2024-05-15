import java.util.*;
import java.util.regex.*;

public class Voisinage implements Iterable<String> {

    private String nom;
    private List<String> coordonnees;

    public Voisinage(String chaineVoisinage) {
    	Pattern pattern = Pattern.compile("\\((-?\\d+),\\s*(-?\\d+)\\)");
        Matcher matcher = pattern.matcher(chaineVoisinage);

        List<String> coordonnees = new ArrayList<>();
        while (matcher.find()) {
            String coordonnee = "(" + matcher.group(1) + ", " + matcher.group(2) + ")";
            coordonnees.add(coordonnee);
        } 	
    	
    	this.nom = chaineVoisinage.substring(0, chaineVoisinage.indexOf(" ="));
        this.coordonnees = coordonnees;
    }

    public String getNom(){
        return nom;
    }

    public List<String> getCoordonnees(){
        return coordonnees;
    }


    @Override
    public Iterator<String> iterator() {
        return coordonnees.iterator();
    }
}
