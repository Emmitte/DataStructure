package test;

public class testY extends testX {
	private int y;
	public testY(){
		y=0;
	}
	public int getY(){
		return y;
	}
	public void showXY(){
		super.setX1(10);
		System.out.println(super.getX()+" "+y);
	}
}
