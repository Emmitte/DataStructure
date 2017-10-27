package sort.vote_system;

public class test {

	public static void main(String[] args) {
		CandidateList caTest=new CandidateList(6,"candData.txt");
		System.out.println("original data:");
		caTest.showList();
		caTest.selectSort();
		System.out.println("sorted data:");
		caTest.showList();
	}

}
