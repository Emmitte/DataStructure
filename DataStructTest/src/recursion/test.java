package recursion;

public class test {

	public static void main(String[] args) {
		Change change=new Change();
		change.decToBin(2);
		System.out.println();
		int bin[]={1,1};
		System.out.println(change.binToDec(bin));
		change.decToAny(36, 16);
		System.out.println();
	}

}
