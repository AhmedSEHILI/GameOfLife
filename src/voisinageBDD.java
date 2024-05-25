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
		 VoisinageExtractor v4 = new VoisinageExtractor("G4={(0,0),(−1,0),(1,0),(0,−1),(0,1)}");
		 
		 
		 
		 voisinageBDD.bddAdd(v1);
		 voisinageBDD.bddAdd(v2);
		 voisinageBDD.bddAdd(v3);
		 voisinageBDD.bddAdd(v4);
		 
	    	TableauDND TDND = new TableauDND(5,5);
	    	
	    	
	    	

		 
	        String chaineRegle = "SI(EQ(COMPTER(G4), 1), SI(SUPEQ(COMPTER(G4), 4), 0, 1), SI(EQ(COMPTER(G4), 2), 1, 0))";

	    	traitementRegle traitement = new traitementRegle(chaineRegle,TDND);
	    	
	    	for(int i = 0; i<5; i++) {
	    		
	    		for(int j = 0; j<5; j++) {
	    			
	    			ArrayList<Integer> a = new ArrayList<Integer>();
	    	    	a.add(i);
	    	    	a.add(j);
	    			TDND.setValue(a, 0);
	    		}
	    	}
	    	
	    	
	    	ArrayList<Integer> a1 = new ArrayList<Integer>();
	    	a1.add(0);
	    	a1.add(1);

	    	ArrayList<Integer> a2 = new ArrayList<Integer>();
	    	a2.add(1);
	    	a2.add(0);

	    	ArrayList<Integer> a3 = new ArrayList<Integer>();
	    	a3.add(1);
	    	a3.add(2);

	    	ArrayList<Integer> a4 = new ArrayList<Integer>();
	    	a4.add(1);
	    	a4.add(4);

	    	ArrayList<Integer> a5 = new ArrayList<Integer>();
	    	a5.add(2);
	    	a5.add(1);
	    	
	    	ArrayList<Integer> a6 = new ArrayList<Integer>();
	    	a6.add(2);
	    	a6.add(2);
	    	
	    	ArrayList<Integer> a7 = new ArrayList<Integer>();
	    	a7.add(3);
	    	a7.add(3);
	    	
	    	ArrayList<Integer> a8 = new ArrayList<Integer>();
	    	a8.add(3);
	    	a8.add(4);
	    	
	    	ArrayList<Integer> a9 = new ArrayList<Integer>();
	    	a9.add(4);
	    	a9.add(0);
	    	
	    	ArrayList<Integer> a10 = new ArrayList<Integer>();
	    	a10.add(4);
	    	a10.add(2);

	    	
	    	
	    	TDND.setValue(a1, 1);
	    	TDND.setValue(a2, 1);
	    	TDND.setValue(a3, 1);
	    	TDND.setValue(a4, 1);
	    	TDND.setValue(a5, 1);
	    	TDND.setValue(a6, 1);
	    	TDND.setValue(a7, 1);
	    	TDND.setValue(a8, 1);
	    	TDND.setValue(a9, 1);
	    	TDND.setValue(a10, 1);
	    	
	    	

			
	    	for(int i = 0; i<5; i++) {
	    		
	    		for(int j = 0; j<5; j++) {

	    	    	ArrayList<Integer> at = new ArrayList<Integer>();
	    	    	at.add(i);
	    	    	at.add(j);
	    		    traitement.appliquerReglePoint(at);
	    		}
	    	}
	    	
	    	for(int i = 0; i<5; i++) {
	    		
	    		for(int j = 0; j<5; j++) {
	    				
	    	    	ArrayList<Integer> at = new ArrayList<Integer>();
	    	    	at.add(i);
	    	    	at.add(j);
	    	    	System.out.print(TDND.getValue(at)+ " | ");
	    		}
	    		System.out.println();
	    	}
	    	    	

	 }
	 
}