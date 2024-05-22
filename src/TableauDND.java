import java.util.*;

public class TableauDND{
	
	private TableauDND[] tab;
	
	private List<Integer> tabBounds = new ArrayList<Integer>();
	 
	//Constructeur
	
	public TableauDND() {}
	 
	 
    public TableauDND(int... args){
    	
    	remplirTabBounds(args);
    	
        tab = new TableauDND[args[0]];
                
        int dim=args.length;
        
        if(args.length>1) {
        	int[] sousDim=Arrays.copyOfRange(args,1,dim);
	        for(int i=0; i<args[0]; i++){
	        	// Création récursive des tableau de dimension n-1 
	            tab[i]=new TableauDND(sousDim);  
	        }
    	}
        else {
        	for(int i=0;i<args[0];i++) {
        		tab[i]= new Cellule(false);
        	}
        }
    }
    
    private void remplirTabBounds(int... args) {
        for (int arg : args) {
            tabBounds.add(arg);
        }
    }
   
    
    
    public boolean getValue(int... coords) {
    	int dim=coords.length;
    	
    	if(dim>1) {
    		return tab[coords[0]].getValue(Arrays.copyOfRange(coords,1,dim));
    	}
    	else {
    		return ((Cellule)tab[coords[0]]).get(); //Exception to manage (java.lang.ArrayIndexOutOfBoundsException)
    	}
    }
    
    public List<Integer> getTabBounds(){
    	
    	return Collections.unmodifiableList((tabBounds)); // sinon si on retourne tabBounds, qui est une référence, on permet 
    	// la modification de ce tableau a partir de l'extérieur
    }
    
/*  
    public static void main(String[] args) {
    	
    	TableauDND TDND = new TableauDND(2,3,2,4);
    	System.out.println(TDND.tabBounds);
    }
*/    
}


