import java.util.*;
import javax.swing.Timer;

import java.awt.event.*;

public class voisinageBDD {

    static Map<String, VoisinageExtractor> map = new HashMap<>();

    static public void bddAdd(VoisinageExtractor V) {
        map.put(V.getNom(), V);
    }

    static public VoisinageExtractor bddGet(String nomVoisinage) {
        return map.get(nomVoisinage);
    }

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

        List<Integer> dims = Arrays.asList(1,101);
        TableauDND TDND = new TableauDND(dims);
        TableauDND TDNDsave;

        String chaineRegle1 = "SI(EQ(COMPTER(G4), 1), SI(SUPEQ(COMPTER(G4), 4), 0, 1), SI(EQ(COMPTER(G4), 2), 1, 0))";
        String chaineRegle2 = "SI(EQ(COMPTER(G4), 1), 1, 0)";
        String chaineRegle3 = "SI(NON(COMPTER(G0)), EQ(COMPTER(G2), 1), 0)";
        String chaineRegle4 = "MUL(SUB(SI(COMPTER(G2*), 1, 0), SI(EQ(COMPTER(G2*), 2), 1, 0)), SUB(1, COMPTER(G0)))";
        
        
        traitementRegle traitement = new traitementRegle(chaineRegle4, TDND);


        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(0);
        a1.add(1);
        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(1);
        a2.add(0);
        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(1);
        a3.add(2);
        ArrayList<Integer> a4 = new ArrayList<>();
        a4.add(1);
        a4.add(4);
        ArrayList<Integer> a5 = new ArrayList<>();
        a5.add(2);
        a5.add(1);
        ArrayList<Integer> a6 = new ArrayList<>();
        a6.add(2);
        a6.add(2);
        
        ArrayList<Integer> a7 = new ArrayList<>();
        a7.add(0);
        a7.add(50);

    /*    TDND.setValue(a1, 1);
        TDND.setValue(a2, 1);
        TDND.setValue(a3, 1);
        TDND.setValue(a4, 1);
        TDND.setValue(a5, 1);
        TDND.setValue(a6, 1);*/
        
        TDND.setValue(a7, 1);
        

        GrilleGraphique grid = new GrilleGraphique(101, 101, 10);
        GrilleGraphique.afficheur1D(101, TDND, grid);
                

       // GrilleGraphique.afficheur2D(5, 5, TDNDsave, grid);
        
      
        
        for(int i =0; i<100; i++) {
        	
        	
        	TDNDsave = traitement.appliquerRegleTableauDND();
        	GrilleGraphique.afficheur1D(101, TDNDsave, grid);
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
