package moneyGame;

import java.util.Scanner;

public class Player {
	CoinStrip strip;
	String name;
	public Player(String name,CoinStrip strip){
		this.name=name;
		this.strip=strip;
	}
	public void move(){
		System.out.println("move number:");
		Scanner sc=new Scanner(System.in);
		int i=sc.nextInt();
		strip.move(i);
	}
	public String getName(){
		return this.name;
	}
}
