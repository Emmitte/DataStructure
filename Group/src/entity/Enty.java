package entity;

public class Enty implements Comparable {
	
	int id;
	double score;
	
	public Enty(int id,double score) {
		this.id = id;
		this.score = score;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(Object o) {
		Enty other = (Enty) o;
		if(this.score < other.score){
			return 1;
		}else if (this.score > other.score) {
			return -1;
		}
		return 0;
	}
	@Override
	public String toString() {
		return "Enty [id=" + id + ", score=" + score + "]";
	}


}
