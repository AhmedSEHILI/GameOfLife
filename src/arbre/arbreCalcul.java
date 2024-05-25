package arbre;

public abstract class arbreCalcul<type> { // j'ai conçu cette classe d'arbre de manière générique pour tout type de donnée, elle est abstraite pasq j'ai déclaré dedans la méthode traiter comme abstraite
											// car le traitement est spécifique a chaque type de données, et elle doit etre implémenter ultérieurement selon le type de donnée de l'arbre
	private Noeud<type> racine;
	
	public arbreCalcul() {
		
		racine = null;
	}
	
	
	
	public void addRacine(Noeud<type> racine) {
		
		this.racine = racine;
		racine.pere = null;
	}
	
	public void addFils(Noeud<type> pere, Noeud<type> op) {
		
		if(pere.op1 == null) {
			pere.op1 = op;
			op.pere = pere;
		}
		else {
			if(pere.op2 == null) {
				pere.op2 = op;
				op.pere = pere;
			}
			else {
				if(pere.op3 == null) {
					pere.op3 = op;
					op.pere = pere;
				}
				// else ==> exception a gerer
			}
		}
		// if added then 
	//	op.pere = pere;

	}
	
	public Noeud<type> getPere(Noeud<type> noeud) {
		
		return noeud.pere;
	}
	
	public Noeud<type> getRacine() {
		return racine;
	}
	

    public abstract void traiter(Noeud<type> noeud); // abstraite pasq le traitement du noeud diffère selon le type générique de la classe

    public void parcours() {
        parcourRec(racine);
    }
    

    private void parcourRec(Noeud<type> noeud) {
        if (noeud != null) {
        	parcourRec(noeud.op1);
        	parcourRec(noeud.op2);
        	parcourRec(noeud.op3);
            traiter(noeud);
        }
    }
    
}