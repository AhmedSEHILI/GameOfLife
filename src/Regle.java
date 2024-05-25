
// Générale en globale 

import java.util.List;

public class Regle {
	
	private int value;
	protected TableauDND tab; 
	protected List<Integer> Point;
	
	public Regle() {}

	public Regle(int value) {
		this.value=value;
	}
	
	
	public Regle(int value, TableauDND tab, List<Integer> Point) {
		this.value=value;
		this.tab=tab;
		this.Point=Point;
	}
	
	public int getValue() {
		return value;
	}
	
} 
