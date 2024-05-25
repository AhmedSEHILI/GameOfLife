
public class NON extends Regle {

	
	private int val1;
	
    public NON(int val1) {
    	this.val1=val1;
    }  
    
    @Override
    public int getValue() {
		return (val1 == 0) ? 1 : 0;
	
    }
    
}

