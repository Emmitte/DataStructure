package algrithm.kmeans.text.byField;

import java.util.Arrays;

public class Item {
	Field[] fields;
	public Item(Field[] fields){
		this.fields=fields;
	}
	public Field[] getFields() {
		return fields;
	}
	public void setFields(Field[] fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "Item [fields=" + Arrays.toString(fields) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fields);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (!Arrays.equals(fields, other.fields))
			return false;
		return true;
	}
	
}
