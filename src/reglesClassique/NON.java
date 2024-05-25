package reglesClassique;

public class NON<type> extends Regle<type> {

		
    public NON() {
    }  
    

    @Override
    public void calculer() {
		setValue((val1 == 0) ? 1 : 0);
	}
    
}

