package sort.vote_system;

public class Candidate extends People implements Comparable{
	private final int noOfRegions=4;
	private int[] votesByRegion;
	private int totalVotes;
	public Candidate(){
		super();
		totalVotes=0;
		votesByRegion=new int[noOfRegions];
	}
	public Candidate(String firstName,String lastName){
		super(firstName,lastName);
		totalVotes=0;
		votesByRegion=new int[noOfRegions];
	}
	public void setVotes(int region,int votes){
		votesByRegion[region-1]=votes;
	}
	public void updateVotesByRegion(int region,int votes){
		votesByRegion[region-1]+=votes;
	}
	public void calculateTotalVotes(){
		totalVotes=0;
		for(int i=0;i<noOfRegions;i++)
			totalVotes+=votesByRegion[i];
	}
	public int getTotalVotes() {
		return totalVotes;
	}
	public void printData(){
		System.out.print(super.toString()+"\t");
		for(int i=0;i<noOfRegions;i++)
			System.out.print(votesByRegion[i]+"\t");
		System.out.println(totalVotes);
	}
	public boolean equals(Candidate other){
		Candidate tmp=other;
		return (this.getFirstName().equals(tmp.getFirstName())&&this.getLastName().equals(tmp.getLastName()));
	}
	@Override
	public int compareTo(Object o) {
		Candidate tmp=(Candidate)o;
		if(this.totalVotes>tmp.totalVotes)
			return 1;
		else if(this.totalVotes<tmp.totalVotes)
			return -1;
		else
			return 0;
	}
}
