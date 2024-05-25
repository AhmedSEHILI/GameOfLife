public class ET extends Regle {
    

	private int val1;
	private int val2;
	
    public ET(int val1, int val2) {
    	this.val1=val1;
    	this.val2=val2;
    }  
    
    @Override 
    public int getValue() {
		return (val1 != 0 && val2 != 0) ? 1 : 0;
	
    }
}
