package de.embl.cmci.obj;

import java.util.ArrayList;

public abstract class Track2D implements ITrack {
	ArrayList<Obj2D> nodes;
	
	Track2D(ArrayList<Obj2D> nodes){
		this.nodes = nodes;
	}
	
	@Override
	public ArrayList<Obj2D> getNodes() {
		return (ArrayList<Obj2D>) nodes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setNodes(ArrayList<?> nodes) {
		this.nodes = (ArrayList<Obj2D>) nodes;
	}
	
	public int len(){
		return nodes.size();
	}
	public void add(Obj2D obj2d){
		nodes.add(obj2d);		
	}

}
