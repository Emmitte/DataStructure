package algrithm.kmeans.text.byField;

public class Field {
	private String name;
	private int type;
	private String value;
	private int boost;
	
	public Field(String name,int type,String value){
		this.name=name;
		this.type=type;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	public int getBoost() {
		return boost;
	}
	public void setBoost(int boost) {
		this.boost = boost;
	}
	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", value=" + value
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + type;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
