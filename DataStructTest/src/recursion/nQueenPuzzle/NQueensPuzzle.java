package recursion.nQueenPuzzle;

public class NQueensPuzzle {
	private int noOfSolutions;
	private int noOfQueens;
	private int[] queensInRow;
	
	public NQueensPuzzle(){
		noOfQueens=8;
		queensInRow=new int[8];
		noOfSolutions=0;
	}
	
	public NQueensPuzzle(int queens){
		this.noOfQueens=queens;
		queensInRow=new int[noOfQueens];
		noOfSolutions=0;
	}
	
	public boolean canPlaceQueen(int k,int i){
		for(int j=0;j<k;j++){
			if((queensInRow[j]==i)||(Math.abs(queensInRow[j]-i)==Math.abs(j-k)))
				return false;
		}
		return true;
	}
	/** 回溯遍历
	 * k:指定放在第k行的皇后
	 * */
	public void queensConfiguration(int k){
		for(int i=0;i<noOfQueens;i++){
			if(canPlaceQueen(k, i)){
				queensInRow[k]=i;
				if(k==noOfQueens-1)
					printConfiguration();
				else
					queensConfiguration(k+1);
			}
		}
	}
	
	public void printConfiguration(){
		noOfSolutions++;
		System.out.print("(");
		for(int i=0;i<noOfQueens-1;i++)
			System.out.print((queensInRow[i]+1)+", ");
		System.out.println((queensInRow[noOfQueens-1]+1)+")");
	}
	
	public int solutionsCount(){
		return noOfSolutions;
	}
	
}
