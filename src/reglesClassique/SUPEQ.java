package reglesClassique;

public class SUPEQ<type> extends Regle<type> {

    public SUPEQ() {

    }  
    

    public void setVal1(int val1) {
		this.val1 = val1;
	}
    
    public void setVal2(int val2) {
		this.val2 = val2;
	}
    
    @Override
    public void calculer() {
		setValue((val1>=val2) ? 1 : 0);
	}
}