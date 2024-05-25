package reglesClassique;

public class SI<type> extends Regle<type> {


    public SI() {
    }  
    
    


    @Override
    public void calculer() {
		setValue(( val1 !=0 ) ? val2 : val3);
	}
    

}