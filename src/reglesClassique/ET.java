package reglesClassique;

public class ET<type> extends Regle<type> {
    

    public ET() {

    }  
    

    @Override
    public void calculer() {
		setValue((val1 != 0 && val2 != 0) ? 1 : 0);
	}
}
