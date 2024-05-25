import reglesClassique.*;

import java.util.List;

import arbre.*;

public  class traitementRegle extends arbreCalcul<Regle<TableauDND>> {

		private List<Integer> Point;
		private TableauDND tab;
		private String  chaineRegle;
		
	    public traitementRegle(String chaineRegle, TableauDND tab) {
			super();
			this.tab = tab;
			this.chaineRegle = chaineRegle;
		}
		
		
	    public void appliquerReglePoint(List<Integer> Point) {
	    	
	    	this.Point = Point;
			creerArbreRegle(chaineRegle);
			parcours();
			changerValeurCellule();
	    	
	    }
		
	    private static boolean isInteger(String str) {
	        try {
	            Integer.parseInt(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
		
		private Noeud<Regle<TableauDND>> createurNoeud(String op) {
			
			String operande = op.trim().toUpperCase();
			Regle<TableauDND> R;
			Noeud<Regle<TableauDND>> N = null;
			
			if(operande.equals("ET")) {
				R = new ET<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("OU")) {
				R = new OU<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("NON")) {
				R = new NON<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("SUP")) {
				R = new SUP<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("SUPEQ")) {
				R = new SUPEQ<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("EQ")) {
				R = new EQ<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("COMPTER")) {
				R = new COMPTER(tab, Point);  // le nom de voisinage est a fournir lors des l'éxamination du fils du noeud compter
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("ADD")) {
				R = new ADD<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("SUB")) {
				R = new SUB<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("MUL")) {
				R = new MUL<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if(operande.equals("SI")) {
				R = new SI<TableauDND>();
				N = new Noeud<Regle<TableauDND>>(R);
			}
			else if (isInteger(operande)) {
				
				R = new Regle<TableauDND>(Integer.parseInt(operande));
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			else if (op.charAt(0) == 'G') {
				
				R = new Regle<TableauDND>(op);
				N = new Noeud<Regle<TableauDND>>(R);
			}
			
			return N;
		}
	
		// il faut implémenter un testeur de validité de la règle (nbr par ouvrantes = fermante ....)
		
	    private void creerArbreRegle(String chaineRegle) { // cette mthd crée l'arbre avec les opératueur dans chaque noeud
	    												  // si le noeud doit etre un opérateur, on met dedans une instatnce de la classe opérateur correspondante
	    												  // si le noeud doit etre un entier, on met dedans une instance de type regle contenant la valeur entière correspondante                                 
	        Noeud<Regle<TableauDND>> currentParent = null;
	        StringBuilder sb = new StringBuilder();

	        for (int i = 0; i < chaineRegle.length(); i++) {
	            char c = chaineRegle.charAt(i);

	            if (c == '(') {
	                if (sb.length() > 0) {
	                    Noeud<Regle<TableauDND>> node = createurNoeud(sb.toString());
	                    if (node != null) {
	                        if (currentParent != null) {
	                            addFils(currentParent, node);
	                        } else {
	                            addRacine(node);
	                        }
	                        currentParent = node;
	                    }
	                    sb.setLength(0);
	                }
	            } else if (c == ')') {
	                if (sb.length() > 0) {
	                    Noeud<Regle<TableauDND>> node = createurNoeud(sb.toString());
	                    if (node != null) {
	                        addFils(currentParent, node);
	                    }
	                    sb.setLength(0);
	                }
	                if (currentParent != null) {
	                    currentParent = currentParent.getPere();
	                }
	            } else if (c == ',') {
	                if (sb.length() > 0) {
	                    Noeud<Regle<TableauDND>> node = createurNoeud(sb.toString());
	                    if (node != null) {
	                        addFils(currentParent, node);
	                    }
	                    sb.setLength(0);
	                }
	            } else {
	                sb.append(c);
	            }
	        }
	    }

		
		
	    
	    
		
		
		public void traiter(Noeud<Regle<TableauDND>> noeud) {
			
			//System.out.println(noeud.getR().getValue());
			
			
			if (noeud.getR() instanceof COMPTER) {
				noeud.getR().setNomVoisinage(noeud.getOp1().getR().getnomVoisinage());
			}
			
			else {
				
				if (noeud.getOp1() != null) noeud.getR().setVal1(noeud.getOp1().getR().getValue());
				if (noeud.getOp2() != null) noeud.getR().setVal2(noeud.getOp2().getR().getValue());
				if (noeud.getOp3() != null) noeud.getR().setVal3(noeud.getOp3().getR().getValue());
				
				
			}
			
			noeud.getR().calculer();
			
			
			//System.out.println(noeud.getR().getValue());
			//System.out.println("---------------------");

		}
		

		
		
		private void changerValeurCellule() {

			tab.setValue(Point,  getRacine().getR().getValue());
		}
		
		
		
		

}
