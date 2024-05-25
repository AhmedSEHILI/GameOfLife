package reglesClassique;

public class MUL<type> extends Regle<type> {


    public MUL() {

    }  
    
    @Override
    public void calculer() {
		setValue(val1*val2);
	}
    
}