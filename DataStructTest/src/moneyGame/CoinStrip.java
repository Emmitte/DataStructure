package moneyGame;

public class CoinStrip {
	private boolean strip[];
	int n1=0;
	public CoinStrip(){
		boolean flag = false;
		while (true) {
			int n;
			n = (int) (Math.random() * 10);
			strip = new boolean[n];
			int x;
			for (int i = 0; i < n; i++) {
				x = n % ((int) (Math.random() * n) + 1);
				//System.out.println(x);
				if (x == 1) {
					strip[i] = true;
					flag = true;
					n1++;
				} else
					strip[i] = false;
			}
			if (flag == true)
				break;
		}
	}
	public void move(int i){
		if(i-1<=0){
			System.out.println("wrong way!again!");
			return;
		}
		if(strip[i-1]==false)
		{
			System.out.println("wrong way!again!");
			return;
		}
		else if(strip[i-2]==true){
			System.out.println("wrong way!again!");
			return;
		}else{
			strip[i-2]=true;
			strip[i-1]=false;
		}
	}
	public void show() {
		System.out.println("status:");
		for(int i=0;i<strip.length;i++){
			if(strip[i]==true)
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
		System.out.println();
	}
	public boolean check(){
		int n=0;
		int i,j;
		for(i=0;i<n1;i++){
			if(strip[i]==false)
				return false;
		}
		/*
		for(j=i;j<strip.length;i++){
			if(strip[j]==true)
				return false;
		}
		*/
		return true;
	}
}
