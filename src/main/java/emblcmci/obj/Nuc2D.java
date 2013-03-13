/**
 * A Nucleus, extending emblcmci.obj.Obj2D
 * Represents a single nucleus within cell.
 * Trial phase still, more generalized design afterwards. 
 * 
 * 20130311 Kota Miura, cmci.embl.de
 * 
 */
package emblcmci.obj;

public class Nuc2D extends Obj2D {

	public int area;
	public int perimeter;
	public int totalint;
	
	public Nuc2D(int x, int y, int id) {
		super(x, y, id);
		
	}
	public Nuc2D(int x, int y, int t, int id) {
		super(x, y, t, id);
		
	}	
	
}
