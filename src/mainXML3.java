import java.util.Arrays;
import java.util.List;

public class mainXML3 {
	
	public static void main(String[] args) {
		
	    
	    
	    
	    
	    
	    
    	VoisinageExtractor v0 = new VoisinageExtractor("G0={(0,0)}");
        VoisinageExtractor v1 = new VoisinageExtractor("G1={(0,0,0,0),(1,0,0,1),(0,1,0,1),(0,1,0,1)}");
        VoisinageExtractor v2 = new VoisinageExtractor("G2={(0,0),(0,-1),(0,1)}");
        VoisinageExtractor v3 = new VoisinageExtractor("G2*={(0,-1),(0,1)}");
        VoisinageExtractor v4 = new VoisinageExtractor("G4={(0,0),(-1,0),(1,0),(0,-1),(0,1)}");
        VoisinageExtractor v5 = new VoisinageExtractor("G8={(0,0,0,0),(-1,0,0,0),(1,0,0,0),(0,-1,0,0),(0,1,0,0),(0,1,0,1),(0,1,1,0)}");
        
        
        

        TraitementXML xml = new TraitementXML("Files/ex3.xml");
        

        voisinageBDD.bddAdd(v0);
        voisinageBDD.bddAdd(v1);
        voisinageBDD.bddAdd(v2);
        voisinageBDD.bddAdd(v3);
        voisinageBDD.bddAdd(v4);
        voisinageBDD.bddAdd(v5);
        
                
         String chaineRegle1 = "SI(EQ(COMPTER(G4), 1), SI(SUPEQ(COMPTER(G4), 4), 0, 1), SI(EQ(COMPTER(G4), 2), 1, 0))";
         String chaineRegle2 = "SI(EQ(COMPTER(G4), 1), 1, 0)";
         String chaineRegle3 = "SI(NON(COMPTER(G0)), EQ(COMPTER(G2), 1), 0)";
         String chaineRegle4 = "MUL(SUB(SI(COMPTER(G2*), 1, 0), SI(EQ(COMPTER(G2*), 2), 1, 0)), SUB(1, COMPTER(G0)))";
        

        List<Integer> dims = xml.getTaille();
        System.out.println(dims);
        
        System.out.println(xml.getVoisinages());
                
        TableauDND TDND = new TableauDND(dims);
        TableauDND TDNDsave;

        xml.afficher();
        traitementRegle traitement = new traitementRegle(xml.getRegle(), TDND);


        
        
/*
        TDND.setValue(a1, 1);
        TDND.setValue(a3, 1);
        TDND.setValue(a4, 1);
        TDND.setValue(a5, 1);
        TDND.setValue(a6, 1);
       */ 
        // TDND.setValue(a7, 1);
        
        
        xml.initTableau(TDND);   //pour random
        

        int dim1 = 20, dim2 = 20;
        
        GrilleGraphique grid = new GrilleGraphique(dim1, dim2, 10);
        //GrilleGraphique.afficheur2D(dim1, dim2, TDND, grid);
        
        for(int i =0; i<100; i++) {
        	
        	
        	TDNDsave = traitement.appliquerRegleTableauDND();
        	GrilleGraphique.afficheurND(dim1, dim2, "(0,0,:,:)", TDND, grid);
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
