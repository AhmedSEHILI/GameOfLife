package reglesClassique;
// Générale en globale 
import java.util.List;
 
public class Regle<type>{ // j étais obligé de rendre cette classe générique car dans ce packaque, le type tableauDND est invisible et on ne peut pas l'importer depuis le default package en java
	
	private int value;
	protected type tab; 
	protected List<Integer> Point;
	protected String nomVoisinage; // pour compter
	
	// val des fils
	protected int val1;
	protected int val2;
	protected int val3;
	
	public Regle() {}

	public Regle(int value) {
		this.value=value;
	}
	
	public Regle(String nomVoisinage) {
		this.nomVoisinage = nomVoisinage;
	}
	
	
	public Regle( type tab, List<Integer> Point) {
		this.tab=tab;
		this.Point=Point;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getnomVoisinage() {
		return nomVoisinage;
	}
	
	public void setNomVoisinage(String nomVoisinage) {
		this.nomVoisinage = nomVoisinage;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	
    public void setVal1(int val1) {
		this.val1 = val1;
	}
    public void setVal2(int val2) {
		this.val2 = val2;
	}
    public void setVal3(int val3) {
		this.val3 = val3;
	}
    
    public void calculer() {} // a redéfinir par les fils
    
    
	
} 
