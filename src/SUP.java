
public class SUP extends Regle {

	private int val1;
	private int val2;
	
    public SUP(int val1, int val2) {
    	this.val1=val1;
    	this.val2=val2;
    }  
    
    @Override
    public int getValue() {
		return (val1>val2) ? 1 : 0;
	
    }
}