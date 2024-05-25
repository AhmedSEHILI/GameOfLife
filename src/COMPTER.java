import java.util.List;

public class COMPTER extends Regle{
	
	private int cpt;	
	
	public COMPTER(String chaineVoisinage) {
		cpt = 0;
		Voisinage v= new Voisinage(super.Point,chaineVoisinage,super.tab.getTabBounds());
		for(List<Integer> e : v) {
			cpt+=tab.getValue(e);
		}
	}
	
    @Override 
    public int getValue() {
		return cpt;
	}
	
}
