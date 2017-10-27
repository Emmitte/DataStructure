package graph;

public class Node {
	public String label;
	public int loc_in;
	public int loc_out;
	
	public Node(String lable,int loc1,int loc2) {
		this.label = lable;
		this.loc_in = loc1;
		this.loc_out = loc2;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getLoc_out() {
		return loc_out;
	}
	public void setLoc_out(int loc_out) {
		this.loc_out = loc_out;
	}
	public int getLoc_in() {
		return loc_in;
	}
	public void setLoc_in(int loc_in) {
		this.loc_in = loc_in;
	}
	@Override
	public String toString() {
		return "Node ["+label+loc_in+" "+loc_out+"]";
		//return "Node [label=" + label + ", loc_in=" + loc_in + ", loc_out=" + loc_out + "]";
	}
	
}
