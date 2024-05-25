package reglesClassique;

public class SUB<type> extends Regle<type> {

	

	public SUB() {

    }  
	

	public void setVal1(int val1) {
		this.val1 = val1;
	}
	
	public void setVal2(int val2) {
		this.val2 = val2;
	}
    
    @Override
    public void calculer() {
		setValue(Math.abs(val1-val2));
	}
}