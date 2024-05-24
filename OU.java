public class OU extends Regle {
	private int val2;
	private int val1;
    public OU(int val1, int val2) {
    	this.val1=val1;
    	this.val2=val2;
    	
    }  
    
    @Override 
    public int getValue() {
		return (val1!=0 || val2!=0) ? 1 : 0;
	}
}
