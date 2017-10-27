package stack.calculation;

public class test {

	public static void main(String[] args) {
		String express;
		Express e=new Express();
		Calculator c=new Calculator();
		express="(6+3)*2=";
		
		e.setInfix(express);
		e.convertToPostfix();
		e.printPostfix();
		c.setExpress(e.getPostfix());
		System.out.println(express+c.postfixExecute());
		
		express="(5*(6+3))+2=";
		e.setInfix(express);
		e.convertToPostfix();
		e.printPostfix();
		c.setExpress(e.getPostfix());
		System.out.println(express+c.postfixExecute());
		
	}

}
