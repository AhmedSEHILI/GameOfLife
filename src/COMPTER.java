import java.util.List;
import reglesClassique.*;

public class COMPTER extends Regle<TableauDND>{ // cette classe ne peut pas rejoindre le package des regles classique car elle est pas générale (utilise un voisinage la dedans)
	
	private int cpt;	
	
	public COMPTER( TableauDND tab, List<Integer> Point) {
		super(tab, Point);
	}
	

	
    
    public void calculer() {
		Voisinage v= new Voisinage(super.Point,super.nomVoisinage,super.tab.getTabBounds());  // nom voisinage ??
		for(List<Integer> e : v) {
			cpt+=tab.getValue(e);
		}
		setValue(cpt);
	}
	
}
