
public class SUB extends Regle {

	
	private int val1;
	private int val2;
	
	public SUB(int val1, int val2) {
    	this.val1=val1;
    	this.val2=val2;
    }  
	
    @Override
    public int getValue() {
		return Math.abs(val1-val2);   //A vérifier resultat négatif
	
    }
}