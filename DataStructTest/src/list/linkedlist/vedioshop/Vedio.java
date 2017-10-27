package list.linkedlist.vedioshop;

public class Vedio implements Comparable {
	private String movieName;
	private String starName;
	private String producerName;
	private String directorName;
	private String companyName;
	private int copyCount;
	public Vedio(){
		copyCount=0;
	}
	public Vedio(String movieName,String starName,String producerName,String directorName,String companyName,int copyCount){
		this.movieName=movieName;
		this.starName=starName;
		this.producerName=producerName;
		this.directorName=directorName;
		this.companyName=companyName;
		this.copyCount=copyCount;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getStarName() {
		return starName;
	}
	public void setStarName(String starName) {
		this.starName = starName;
	}
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCopyCount() {
		return copyCount;
	}
	public void setCopyCount(int copyCount) {
		this.copyCount = copyCount;
	}
	@Override
	public String toString() {
		return "Vedio [movieName=" + movieName + ", starName=" + starName
				+ ", producerName=" + producerName + ", directorName="
				+ directorName + ", companyName=" + companyName
				+ ", copyCount=" + copyCount + "]";
	}
	public void hireMovie(){
		if(copyCount<1)
			System.out.println("Cannot rent out movie.");
		else
			copyCount--;
	}
	public void payback(){
		copyCount++;		
	}
	public boolean isCanHire(){
		if(copyCount>0)
			return true;
		else
			return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + copyCount;
		result = prime * result
				+ ((directorName == null) ? 0 : directorName.hashCode());
		result = prime * result
				+ ((movieName == null) ? 0 : movieName.hashCode());
		result = prime * result
				+ ((producerName == null) ? 0 : producerName.hashCode());
		result = prime * result
				+ ((starName == null) ? 0 : starName.hashCode());
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
		Vedio other = (Vedio) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (copyCount != other.copyCount)
			return false;
		if (directorName == null) {
			if (other.directorName != null)
				return false;
		} else if (!directorName.equals(other.directorName))
			return false;
		if (movieName == null) {
			if (other.movieName != null)
				return false;
		} else if (!movieName.equals(other.movieName))
			return false;
		if (producerName == null) {
			if (other.producerName != null)
				return false;
		} else if (!producerName.equals(other.producerName))
			return false;
		if (starName == null) {
			if (other.starName != null)
				return false;
		} else if (!starName.equals(other.starName))
			return false;
		return true;
	}
	public int compareTo(Object o) {
		// TODO 自动生成的方法存根
		return 0;
	}
	
}
