import java.util.*;


public class Voisinage implements Iterator<List<Integer>>, Iterable<List<Integer>> {

	private List<Integer> point;
	public List<List<Integer>> coordonneesVoisinage;
	private int index;
	private int nbrCoords;
	private List<Integer> tabBounds;
	
	public Voisinage(List<Integer> point, String nomVoisinage, List<Integer> tabBounds) {
		
		this.index = 0;
		this.point = point;
		//Vois = new VoisinageExtractor(chaineVoisinage);
		coordonneesVoisinage = voisinageBDD.bddGet(nomVoisinage).getCoordonnees(); // important if voisinageBDD.bddGet(nomVoisinage) == null  gérer l'exception
		nbrCoords = coordonneesVoisinage.size();
		this.tabBounds = tabBounds;
	}
	
	
	public void reset(ArrayList<Integer> point) {
		
		this.point = point;
	}
	
	private boolean isOutOfBoundsVerification() {
		
		for(int i=0; i<tabBounds.size(); i++) {
			if(point.get(i)+coordonneesVoisinage.get(index).get(i) >= tabBounds.get(i)
					|| point.get(i)+coordonneesVoisinage.get(index).get(i) <0)
				return true;
		}
		return false;
	}
	
	@Override
	public boolean hasNext() {
		
		while(index < nbrCoords && isOutOfBoundsVerification()) index += 1;
		return (index < nbrCoords);
	}

	@Override
	public List<Integer> next() {
		List<Integer> list = new ArrayList<Integer>();
	 	for(int i=0;  i<tabBounds.size(); i++) 
	 		list.add(point.get(i)+coordonneesVoisinage.get(index).get(i));
	 	
		index+=1;
		
		return Collections.unmodifiableList(list);
	}
	
	@Override
	public Iterator<List<Integer>> iterator() {
		return this;
	}

}