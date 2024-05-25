package arbre; 

public class Noeud <type> {

	private type R;
	protected Noeud<type> pere;
	protected Noeud<type> op1, op2, op3;
	
	public Noeud(type R) {
		
		this.setR(R);
		pere = null;
		op1 = op2 = op3 = null;
	}

	public type getR() {
		return R;
	}

	public void setR(type r) {
		R = r;
	}
	
	public Noeud<type> getPere() {
		return pere;
	}
	
	public Noeud<type> getOp1() {
		return op1;
	}
	
	public Noeud<type> getOp2() {
		return op2;
	}
	
	public Noeud<type> getOp3() {
		return op3;
	}
	
	
}
