import java.util.*;
import java.util.regex.*;

public class Voisinage implements Iterator<List<Integer>>, Iterable<List<Integer>> {

    private String nom;
    public List<Integer> startPoint;
    private List<List<Integer>> coordonnees;
    private int index=0; //Compteur des voisins parcouru
   
    public Voisinage(String chaineVoisinage) {
        Pattern pattern = Pattern.compile("\\((-?\\d+(,\\s*-?\\d+)*)\\)");
        Matcher matcher = pattern.matcher(chaineVoisinage);

        List<List<Integer>> coordonnees = new ArrayList<>();
        while (matcher.find()) {
            List<Integer> coord = new ArrayList<>();
            String[] valeurs = matcher.group(1).split(",\\s*");
            for (String valeur : valeurs) {
                coord.add(Integer.parseInt(valeur));
            }
            coordonnees.add(coord);
        }
        this.nom = chaineVoisinage.substring(0, chaineVoisinage.indexOf("=")).trim(); //trim pour éliminer les espaces blancs
        this.coordonnees = coordonnees;
    }

    public String getNom() {
        return nom;
    }
    
    //Retourne les coordonnées de tous les voisins
    public List<List<Integer>> getCoordonnees() {
        return coordonnees;
    }
    
    //Retourne vrai si on a des voisins non parcouru
    @Override
	public boolean hasNext() {
    	int size=this.coordonnees.size();
		return index<size;
	}

    //Retourne le prochain voisin à parcourir
	@Override
	public List<Integer> next(){
		List<Integer> sum,voisin;
		sum = new ArrayList<Integer>();
		voisin=this.coordonnees.get(index++);
		
		for(int i=0;i<startPoint.size();i++ ) {
			sum.add(voisin.get(i)+startPoint.get(i));
		}
		return sum;
	}
	
	public void reset(List<Integer>newa)
	{
		startPoint=newa;
		index=0;
	}

	@Override
	public Iterator<List<Integer>> iterator() {
		return this;
	}

    public static void main(String[] args) {
        Voisinage voisinage = new Voisinage("G2* = {(0,0,0,0),(1,1,2,1),(1,2,1,1),(1,7,1,2)}");
        voisinage.startPoint= Arrays.asList(1,2,3,4);
        System.out.println("Nom : " + voisinage.getNom());
        System.out.println("Coordonnées : " + voisinage.getCoordonnees());
        System.out.println("Size : " + voisinage.getCoordonnees().size());
        System.out.println(voisinage.next());
        System.out.println(voisinage.next());
        System.out.println(voisinage.next());
        System.out.println(voisinage.next());
        System.out.println(voisinage.hasNext());
    }
}



