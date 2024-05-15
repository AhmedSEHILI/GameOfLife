import java.util.*;

public class TableauDND{
	
	private TableauDND[] tab;
	 
	//Constructeur
    public TableauDND(int... args){
    	
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
    
    public TableauDND() {}
    
    
    public boolean getValue(int... coords) {
    	int dim=coords.length;
    	
    	if(dim>1) {
    		return tab[coords[0]].getValue(Arrays.copyOfRange(coords,1,dim));
    	}
    	else {
    		return getValue(coords[0]);
    	}
    }
    
    
    
    public static void main(String[] args) {
      TableauDND tab = new TableauDND(9,6,5,7,4);
      
      tab.getValue(5,1,2,1,4);
      
  }
    
    
    
    
    
}
