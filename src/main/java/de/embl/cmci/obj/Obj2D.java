package de.embl.cmci.obj;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public abstract class Obj2D implements Obj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Vector2D pv;
	public int t;	//timepoint
	public int id;
	public int nexid;

	public Obj2D(int id){
		this.id = id;	
	}
	public Obj2D(int x, int y, int id){
		setCoord(x, y);
		this.id = id;	
	}
	
	public Obj2D(int x, int y, int t, int id){
		setCoord(x, y);
		this.t= t;
		this.id = id;
			
	}
	public Vector2D getCoord() {
		return pv;
	}
	public void setCoord(Vector2D vec) {
		this.pv = (Vector2D) vec;

	}
	public void setCoord(int x, int y) {
		Vector2D vec2d = new Vector2D((double) x, (double) y);
		setCoord(vec2d);
	}
	public void setCoord(int x, int y, int t) {
		setCoord(x, y);
		setT(t);
	}
	public void setT(int t){
		this.t= t;
	}	
}
