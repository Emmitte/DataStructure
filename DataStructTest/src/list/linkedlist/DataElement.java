package list.linkedlist;

public abstract class DataElement {
	public abstract boolean equals(DataElement otherElement);
	public abstract int compareTo(DataElement otherElement);
	public abstract void makeCopy(DataElement otherElement);
	public abstract DataElement getCopy();
}
