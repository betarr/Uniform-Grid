package model;

import java.util.ArrayList;
import java.util.List;

public class GridSquere {

	private List<Integer> objectIds;
	
	public GridSquere() {
		this.objectIds = new ArrayList<Integer>();
	}
	
	public void addId(int id) {
		this.objectIds.add(id);
	}
	
	public void removeId(int id) {
		for (int i = 0; i < this.objectIds.size(); i++) {
			if (this.objectIds.get(i) == id) {
				this.objectIds.remove(i);
			}
		}
	}
	
	public boolean containsId(int id) {
		return this.objectIds.contains(id);
	}
	
	public int getNumberOfIds() {
		return this.objectIds.size();
	}
	
	public List<Integer> getIds() {
		return this.objectIds;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < this.objectIds.size(); i++) {
			result += (i == 0) ? "" : ";";
			result += String.valueOf(this.objectIds.get(i));
		}
		return result;
	}
}
