/**
 * A Nucleus, extending emblcmci.obj.Obj2D
 * Represents a single nucleus within cell.
 * Trial phase still, more generalized design afterwards. 
 * 
 * 20130311 Kota Miura, cmci.embl.de
 * 
 */
package emblcmci.obj;

import java.text.NumberFormat;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;

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
	@Override
	public Vector add(Vector arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector add(double arg0, Vector arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double distance(Vector arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double distance1(Vector arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double distanceInf(Vector arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double distanceSq(Vector arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double dotProduct(Vector arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getNorm() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getNorm1() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getNormInf() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getNormSq() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Space getSpace() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector getZero() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isInfinite() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isNaN() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Vector negate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector normalize() throws MathArithmeticException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector scalarMultiply(double arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector subtract(Vector arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Vector subtract(double arg0, Vector arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(NumberFormat arg0) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
