package reglesClassique;

public class ADD<type> extends Regle<type> {


    public ADD() {
    }  

    @Override
    public void calculer() {
		setValue((val1==val2) ? 1 : 0);
	}
}