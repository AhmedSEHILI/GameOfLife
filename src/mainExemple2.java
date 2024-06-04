import java.util.Arrays;
import java.util.List;

public class mainExemple2 {


    public static void main(String[] args) {
    	
    	VoisinageExtractor v0 = new VoisinageExtractor("G0={(0,0)}");
        VoisinageExtractor v1 = new VoisinageExtractor("G1={(0,0,0,0),(1,0,0,1),(0,1,0,1),(0,1,0,1)}");
        VoisinageExtractor v2 = new VoisinageExtractor("G2={(0,0),(0,-1),(0,1)}");
        VoisinageExtractor v3 = new VoisinageExtractor("G2*={(0,-1),(0,1)}");
        VoisinageExtractor v4 = new VoisinageExtractor("G4={(0,0),(-1,0),(1,0),(0,-1),(0,1)}");

        voisinageBDD.bddAdd(v0);
        voisinageBDD.bddAdd(v1);
        voisinageBDD.bddAdd(v2);
        voisinageBDD.bddAdd(v3);
        voisinageBDD.bddAdd(v4);
        
        String chaineRegle1 = "SI(EQ(COMPTER(G4), 1), SI(SUPEQ(COMPTER(G4), 4), 0, 1), SI(EQ(COMPTER(G4), 2), 1, 0))";
        String chaineRegle2 = "SI(EQ(COMPTER(G4), 1), 1, 0)";
        String chaineRegle3 = "SI(NON(COMPTER(G0)), EQ(COMPTER(G2), 1), 0)";
        String chaineRegle4 = "MUL(SUB(SI(COMPTER(G2*), 1, 0), SI(EQ(COMPTER(G2*), 2), 1, 0)), SUB(1, COMPTER(G0)))";
        

        List<Integer> dims = Arrays.asList(101,101);
        TableauDND TDND = new TableauDND(dims);
        TableauDND TDNDsave;

        traitementRegle traitement = new traitementRegle(chaineRegle2, TDND);


        List<Integer> a1 = Arrays.asList(0,0);
        List<Integer> a2 = Arrays.asList(0,100);
        List<Integer> a3 = Arrays.asList(100,0);
        List<Integer> a4 = Arrays.asList(100,100);
        List<Integer> a5 = Arrays.asList(11,52);
        List<Integer> a6 = Arrays.asList(33,4);
        List<Integer> a7 = Arrays.asList(50,50);
        
        
        
        TDND.setValue(a1, 1);
        TDND.setValue(a2, 1);
        TDND.setValue(a3, 1);
        TDND.setValue(a4, 1);
    /*    TDND.setValue(a6, 1);
       */ 
        TDND.setValue(a7, 1);
        

        int dim1 = 101, dim2 = 101;
        
        GrilleGraphique grid = new GrilleGraphique(dim1, dim2, 10);
        GrilleGraphique.afficheur2D(dim1, dim2, TDND, grid);
        
        for(int i =0; i<100; i++) {
        	
        	
        	TDNDsave = traitement.appliquerRegleTableauDND();
        	GrilleGraphique.afficheur2D(dim1, dim2, TDND, grid);
            TDND = TableauDND.copyTableauDND(TDNDsave);
            traitement.setTab(TDND);
            
            try {    // juste pour faire un interval deux temps entre les affichage: (ça marche pas sans les try catch ou throws)
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        

    }

}
