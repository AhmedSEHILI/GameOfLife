import java.util.List;

public class mainXML2 {


    public static void main(String[] args) {
    	
    	VoisinageExtractor v0 = new VoisinageExtractor("G0={(0,0)}");
        VoisinageExtractor v1 = new VoisinageExtractor("G1={(0,0,0,0),(1,0,0,1),(0,1,0,1),(0,1,0,1)}");
        VoisinageExtractor v2 = new VoisinageExtractor("G2={(0,0),(0,-1),(0,1)}");
        VoisinageExtractor v3 = new VoisinageExtractor("G2*={(0,-1),(0,1)}");
        VoisinageExtractor v4 = new VoisinageExtractor("G4={(0,0),(-1,0),(1,0),(0,-1),(0,1)}");
        
        
        

        TraitementXML xml = new TraitementXML("Files/GameOfLife.xml");
        

        voisinageBDD.bddAdd(v0);
        voisinageBDD.bddAdd(v1);
        voisinageBDD.bddAdd(v2);
        voisinageBDD.bddAdd(v3);
        voisinageBDD.bddAdd(v4);
        

        List<Integer> dims = xml.getTaille();
        System.out.println(dims);
        
        System.out.println(xml.getVoisinages());
                
        TableauDND TDND = new TableauDND(dims);
        TableauDND TDNDsave;

        xml.afficher();
        traitementRegle traitement = new traitementRegle(xml.getRegle(), TDND);

        
        
        xml.initTableau(TDND);  
        

        int dim1 = 101, dim2 = 101;
        
        GrilleGraphique grid = new GrilleGraphique(dim1, dim2, 10);
        
        
        for(int i =0; i<1000; i++) {
        	
        	
        	TDNDsave = traitement.appliquerRegleTableauDND();
  
        	GrilleGraphique.afficheur2D(dim1, dim2, TDND, grid);
        	        	
        	if(TableauDND.comparerTabDND(TDND, TDNDsave)) break;
        	
            TDND = TableauDND.copyTableauDND(TDNDsave);
            
            traitement.setTab(TDND);
            
            try {   
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
        }
                
        

    }

}