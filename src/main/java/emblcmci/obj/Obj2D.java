package emblcmci.obj;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public abstract class Obj2D implements Obj {
	public int x;
	public int y;
	public int t;	//timepoint
	protected Vector2D posvec;
	
	@Override
	public Vector2D getCoord() {
		return posvec;
	}
	@Override
	public void setCoord(Vector vec) {
		this.posvec = (Vector2D) vec;

	}
	public void setCoord(int x, int y) {
		Vector vec2d = new Vector2D((double) x, (double) y);
		setCoord(vec2d);
	}
	public void setCoord(int x, int y, int t) {
		setCoord(x, y);
	}
	public void setT(int t){
		this.t= t;
	}	
}
