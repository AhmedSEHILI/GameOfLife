
public class Cellule extends TableauDND{
	
	private boolean val;
	
	public Cellule(boolean data ) {
		
		this.val=data;
	}
	
	public boolean get() {
		return val;
	}
	
	public void set(boolean data) {
		this.val=data;
	}
	
}
