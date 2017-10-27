package moneyGame;

public class test {

	public static void main(String[] args) {
		CoinStrip strip=new CoinStrip();
		while (strip.check()==true){
			strip=new CoinStrip();
		}
		strip.show();
		Player player1=new Player("player1",strip);
		Player player2=new Player("player2",strip);
		while(strip.check()==false){
			System.out.println(player1.getName()+" play:");
			player1.move();
			strip.show();
			if(strip.check()==true){
				System.out.println(player1.getName()+" win!");
				break;
			}
			System.out.println(player2.getName()+" play:");
			player2.move();
			strip.show();
			if(strip.check()==true){
				System.out.println(player2.getName()+" win!");
				break;
			}
		}
	}

}
