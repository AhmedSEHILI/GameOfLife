import java.util.*;

public class voisinageBDD {

	static Map<String, VoisinageExtractor> map = new HashMap<>(); // j ai choisi une map pour éviter les doublons
	
	static public void bddAdd(VoisinageExtractor V) {
		
		map.put(V.getNom(), V);  // on garantie qu'il y aura pas de doublons par rapport au nom du voisinage
	}
	
	static public VoisinageExtractor bddGet(String nomVoisinage) {
		
		return map.get(nomVoisinage); // recherche par key //
	}
	
	
	
	 public static void main(String[] args) {
		 
		 VoisinageExtractor v1 = new VoisinageExtractor("G1={(0,0,0,0),(1,0,0,1),(0,1,0,1),(0,1,0,1)}");
		 VoisinageExtractor v2 = new VoisinageExtractor("G2={(0,0,0,0),(1,0,0,1),(0,1,0,1)}");
		 VoisinageExtractor v3 = new VoisinageExtractor("G2*={(0,0,0),(1,0,1),(1,0,1)}");
		 
		 voisinageBDD.bddAdd(v1);
		 voisinageBDD.bddAdd(v2);
		 voisinageBDD.bddAdd(v3);
		 
	    	TableauDND TDND = new TableauDND(3,3,2,4);
	    	ArrayList<Integer> al = new ArrayList<Integer>();
	    	al.add(1);
	    	al.add(1);
	    	al.add(1);
	    	al.add(1);
	    	
	    	ArrayList<Integer> al2 = new ArrayList<Integer>();
	    	al2.add(2);
	    	al2.add(0);
	    	al2.add(0);
	    	al2.add(2);
	    	
	    	
	    	TDND.setValue(al, 1);
	    	TDND.setValue(al2, 0);
	    	
	    	

		 
	        String chaineRegle = "SI(EQ(COMPTER(G1), 1),SI(SUPEQ(COMPTER(G1), 4), 0, 1),SI(EQ(COMPTER(G1), 2), 1, 0))";
	    	traitementRegle traitement = new traitementRegle(chaineRegle,TDND);
	    	
	    	traitement.appliquerReglePoint(al);
	    	
	    	System.out.println(TDND.getValue(al));
	    	
	    	traitement.appliquerReglePoint(al2);
	    	
	    	System.out.println(TDND.getValue(al2));
	 }
	 
}
