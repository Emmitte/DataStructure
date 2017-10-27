package sort.vote_system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CandidateList {
	private Candidate candidates[];
	private int n;
	public CandidateList(int n,String path){
		this.n=n;
		candidates=new Candidate[n];
		getData(path);
	}
	private void getData(String path){
		FileReader fr=null;
		BufferedReader br=null;
		String line=null;
		Candidate cadidate;
		int i=0;
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			while ((line=br.readLine())!=null) {
				String strArray[]=line.split("\t");
				//System.out.println(line);
				String firstName,lastName;
				int region,votes;
				firstName=strArray[0];
				lastName=strArray[1];
				region=Integer.parseInt(strArray[2]);
				votes=Integer.parseInt(strArray[3]);
				cadidate=new Candidate(firstName, lastName);
				cadidate.setVotes(region, votes);
				//System.out.print("candidate:");
				//cadidate.printData();
				boolean flag=false;
				int loc=0;
				if(i==0){
					candidates[i]=cadidate;
					i++;
					continue;
				}
				for(int j=0;j<i;j++){
					//candidates[j].printData();
					if(cadidate.equals(candidates[j])){
						//cadidate.printData();
						flag=true;
						loc=j;
						break;
					}
				}
				//System.out.println("i="+i+" loc="+loc+" flag="+flag);
				if(flag==true)
				{
					candidates[loc].updateVotesByRegion(region, votes);
					candidates[loc].calculateTotalVotes();
				}
				else
				{
					candidates[i]=cadidate;
					candidates[i].calculateTotalVotes();
					i++;
				}
			}
			br.readLine();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showList(){
		for(int i=0;i<n-1;i++)
			candidates[i].printData();
	}
	private void swap(int i,int j){
		Candidate tmp;
		tmp=candidates[i];
		candidates[i]=candidates[j];
		candidates[j]=tmp;
	}
	public void selectSort() {
		boolean flag=false;
		int loc = 0;
		for(int i=0;i<n;i++){
			Candidate min=candidates[i];
			for(int j=i+1;j<n;j++)
				if(candidates[j].getTotalVotes()<min.getTotalVotes())
				{
					flag=true;
					loc=j;
					min=candidates[j];
				}
			if(flag==true){
				swap(i, loc);
				flag=false;
			}
		}
	}
}
