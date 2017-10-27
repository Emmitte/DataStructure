package recursion;

public class Change {
	public Change(){
		
	}
	public void decToBin(int dec){
		if(dec>0){
			decToBin(dec/2);
			System.out.print(dec%2);
		}
	}
	public void decToAny(int dec,int n){
		if(n<=10&&dec>0){
			decToAny(dec/n, n);
			System.out.print(dec%n);
		}
		if(n>10&&n<=36&&dec>0){
			decToAny(dec/n, n);
			int tmp=dec%n;
			//System.out.println("tmp="+tmp);
			if(tmp<10)
				System.out.print(tmp);
			else{
				int tmp2=tmp-10;
				//char show=('A'+tmp2);
				System.out.print((char)('A'+tmp2));
			}
		}
	}
	public int binToDec(int[] bin){
		int sum=0;
		int tmp=0;
		int j=bin.length-1;
		for(int i=0;i<bin.length;i++){
			tmp=bin[i]*index(2, j);
			sum+=tmp;
			j--;
		}
		return sum;
	}
	private int index(int a,int b){
		int sum=1;
		for(int i=0;i<b;i++){
			sum*=a;
		}
		return sum;
	}
}
