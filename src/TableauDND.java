import java.util.*;

public class TableauDND{
	
	private List<TableauDND> tab; // Utilisation de List au lieu de tableau
	
	private List<Integer> tabBounds = new ArrayList<Integer>();
	 
	// Constructeur
	public TableauDND() {}
	 
    public TableauDND(List<Integer> args) {
        remplirTabBounds(args);
        tab = new ArrayList<>(args.get(0));

        int dim = args.size();

        if (dim > 1) {
            List<Integer> sousDim = args.subList(1, dim);
            for (int i = 0; i < args.get(0); i++) {
                // Création récursive des tableaux de dimension n-1
                tab.add(new TableauDND(sousDim));
            }
        } else {
            for (int i = 0; i < args.get(0); i++) {
                tab.add(new Cellule(0));
            }
        }
    }

    
    private void remplirTabBounds(List<Integer> args) {
        tabBounds.addAll(args);
    }
   
	public int getValue(List<Integer> coords) {
		int dim = coords.size();
    	
		if (dim > 1) {
			return tab.get(coords.get(0)).getValue(coords.subList(1, dim));
		} else {
			return ((Cellule) tab.get(coords.get(0))).get(); // Exception to manage (java.lang.ArrayIndexOutOfBoundsException)
		}
	}
	
    public void setValue(List<Integer> coords, int val) {
        int dim = coords.size();
        
        if (dim > 1) {
            tab.get(coords.get(0)).setValue(coords.subList(1, dim), val);
        } else {
            ((Cellule) tab.get(coords.get(0))).set(val); 
        }
    }
	
	public List<TableauDND> getTDND(){
		//return Collections.unmodifiableList(tab);
		return Collections.unmodifiableList(tab);
	}
    
	public List<TableauDND> getTableauDND() {  /// ????????
		return tab;
	}
    
	public List<Integer> getTabBounds() {
		return Collections.unmodifiableList(tabBounds); // sinon si on retourne tabBounds, qui est une référence, on permet 
		// la modification de ce tableau a partir de l'extérieur
	}
	
	
	
    public static void parcourirTableau(TableauDND tableau) {
    	List<Integer> coords = new ArrayList<Integer>();
        if (tableau.getTabBounds().size() == 1) {
            for (int i = 0; i < tableau.getTabBounds().get(0); i++) {
                coords.add(i);
                System.out.print(tableau.getValue(coords) + " | ");
                coords.remove(coords.size() - 1);
            }
            System.out.println();
        } else {
            for (int i = 0; i < tableau.getTabBounds().get(0); i++) {
                coords.add(i);
                parcourirTableau(tableau.getTableauDND().get(i));
                coords.remove(coords.size() - 1);
            }
        }
    }
    
    
    public static void resetTDND(TableauDND tableau) {
    	List<Integer> coords = new ArrayList<Integer>();
        if (tableau.getTabBounds().size() == 1) {
            for (int i = 0; i < tableau.getTabBounds().get(0); i++) {
                coords.add(i);
                tableau.setValue(coords, 0);
                coords.remove(coords.size() - 1);
            }
        } else {
            for (int i = 0; i < tableau.getTabBounds().get(0); i++) {
                coords.add(i);
                parcourirTableau(tableau.getTableauDND().get(i));
                coords.remove(coords.size() - 1);
            }
        }
    }
    

    

    public static TableauDND copyTableauDND(TableauDND original) {
        return copyRecursive(original);
    }

    private static TableauDND copyRecursive(TableauDND original) {
        List<Integer> bounds = original.getTabBounds();
        TableauDND copy = new TableauDND(bounds);

        if (bounds.size() == 1) {
            for (int i = 0; i < bounds.get(0); i++) {
                List<Integer> coords = Arrays.asList(i);
                int value = original.getValue(coords);
                copy.setValue(coords, value);
            }
        } else {
            for (int i = 0; i < bounds.get(0); i++) {
                TableauDND subOriginal = original.getTableauDND().get(i);
                TableauDND subCopy = copyRecursive(subOriginal);
                copy.getTableauDND().set(i, subCopy);
            }
        }
        
        return copy;
    }

    


    public static boolean comparerTabDND(TableauDND tab1, TableauDND tab2) {
        if (!tab1.getTabBounds().equals(tab2.getTabBounds())) {
            return false;
        }

        List<Integer> bounds = tab1.getTabBounds();

        if (bounds.size() == 1) {
            for (int i = 0; i < bounds.get(0); i++) {
                List<Integer> coords = Arrays.asList(i);
                if (tab1.getValue(coords) != tab2.getValue(coords)) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < bounds.get(0); i++) {
                TableauDND subTab1 = (TableauDND) tab1.getTableauDND().get(i);
                TableauDND subTab2 = (TableauDND) tab2.getTableauDND().get(i);
                if (!comparerTabDND(subTab1, subTab2)) {
                    return false;
                }
            }
        }

        return true;
    }
    
    
    
}


