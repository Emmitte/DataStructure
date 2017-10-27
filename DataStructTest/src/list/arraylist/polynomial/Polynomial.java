package list.arraylist.polynomial;

public class Polynomial {
	private int coefficient;
	private int index;
	public Polynomial(int coefficient,int index){
		this.coefficient=coefficient;
		this.index=index;
	}
	public int getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString() {
		return  coefficient + "x^" + index;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coefficient;
		result = prime * result + index;
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynomial other = (Polynomial) obj;
		if (coefficient != other.coefficient)
			return false;
		if (index != other.index)
			return false;
		return true;
	}	
}
