package reglesClassique;

public class EQ<type> extends Regle<type> {


    public EQ() {
    }  
    

    
    @Override
    public void calculer() {
		setValue((val1==val2) ? 1 : 0);
	}
    
}