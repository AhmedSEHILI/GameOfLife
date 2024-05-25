package reglesClassique;

public class OU<type> extends Regle<type> {
	

    public OU() {
    }  
    
    

    @Override
    public void calculer() {
		setValue((val1!=0 || val2!=0) ? 1 : 0);
	}
}
