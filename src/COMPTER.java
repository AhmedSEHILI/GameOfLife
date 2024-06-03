
import java.util.List;

import reglesClassique.Regle;

public class COMPTER extends Regle<TableauDND>{ // cette classe ne peut pas rejoindre le package des regles classique car elle est pas générale (utilise un voisinage la dedans)
	
	
	
	public COMPTER( TableauDND tab, List<Integer> Point) {
		super(tab, Point);
	}
	

	
    
    public void calculer() {
		Voisinage v = new Voisinage(super.Point,super.nomVoisinage,super.tab.getTabBounds());  // nom voisinage ??
		int cpt = 0;	
		
		for(List<Integer> e : v) {
			cpt+=tab.getValue(e);
		}
		
		
		setValue(cpt);
		

	}
	
}
