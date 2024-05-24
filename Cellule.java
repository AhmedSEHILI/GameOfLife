
public class Cellule extends TableauDND{
	
	private int val;
	
	public Cellule(int data) {
		
		this.val=data;
	}
	
	public int get() {
		return val;
	}
	
	public void set(int data) {
		this.val=data;
	}
	
}