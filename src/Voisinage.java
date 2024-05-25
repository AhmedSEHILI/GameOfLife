import java.util.*;


public class Voisinage implements Iterator<List<Integer>>, Iterable<List<Integer>> {

	private List<Integer> point;
	private List<List<Integer>> coordonneesVoisinage;
	private int index;
	private int nbrCoords;
	private VoisinageExtractor Vois;
	private List<Integer> tabBounds;
	
	public Voisinage(List<Integer> point, String chaineVoisinage, List<Integer> tabBounds) {
		
		this.index = 0;
		this.point = point;
		Vois = new VoisinageExtractor(chaineVoisinage);
		coordonneesVoisinage = Vois.getCoordonnees();
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
	
	
/*	
	public static void main(String[] args) {

    	TableauDND TDND = new TableauDND(3,3,2,4);
    	ArrayList<Integer> al = new ArrayList<Integer>();
    	al.add(1);
    	al.add(1);
    	al.add(1);
    	al.add(1);
    	

    	Voisinage vois = new Voisinage(al, "G2*={(0,0,0,0),(1,0,0,1),(0,1,0,1)}", TDND.getTabBounds());
   
    	for(List<Integer> l : vois) {
    		System.out.println(l);
    	}

	}
*/
}