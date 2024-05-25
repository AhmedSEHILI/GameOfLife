
public class arbreCalcul {

	Noeud racine;
	
	public arbreCalcul() {
		
		racine = null;
	}
	
	
	
	public void addRacine(Noeud racine) {
		
		this.racine = racine;
	}
	
	public void addFils(Noeud pere, Noeud op1, Noeud op2, Noeud op3) {
		
		pere.op1 = op1;
		pere.op2 = op2;
		pere.op3 = op3;
	}
	


    public void parcours() {
        parcourRec(racine);
    }
    
    // Méthode récursive pour la traversée en large
    private void parcourRec(Noeud noeud) {
        if (noeud != null) {
        	parcourRec(noeud.op1);
        	parcourRec(noeud.op2);
        	parcourRec(noeud.op3);
            System.out.print(noeud.R.getValue() + " ");
        }
    }
    
 /*   
    public static void main(String[] args) {
        arbreCalcul arbre = new arbreCalcul();
        
        Regle r1 = new Regle(1);
        Regle r2 = new Regle(2);
        Regle r3 = new Regle(3);
        Regle r4 = new Regle(4);
        
        Regle r5 = new Regle(5);
        Regle r6 = new Regle(6);
        
        Noeud n1 = new Noeud(r1);
        Noeud n2 = new Noeud(r2);
        Noeud n3 = new Noeud(r3);
        Noeud n4 = new Noeud(r4);
        
        Noeud n5 = new Noeud(r5);
        Noeud n6 = new Noeud(r6);
        
      
        
        
        arbre.addRacine(n1);
        
        arbre.addFils(n1, n2, n3, n4);
        
        arbre.addFils(n4, n5, n6, null);

        arbre.parcours();
    }
    
    */
}