package stack.calculation;

import java.util.Stack;

public class Calculator {
	private String express;
	private Stack<Double> s=new Stack<Double>();
	
	public Calculator(){
		
	}
	
	public Calculator(String express){
		this.express=express;
	}
	
	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express=null;
		this.express = express;
	}

	public double postfixExecute(){
		int i=0;
		double a,b,c;		
		while(express.charAt(i)!='\0'){
			//System.out.println("express[i]="+express.charAt(i));
			if(express.charAt(i)=='='){
				return s.pop();
			}
			else if(express.charAt(i)!='+'&&express.charAt(i)!='-'&&express.charAt(i)!='*'&&express.charAt(i)!='/'){
				String tmp=String.valueOf(express.charAt(i));
				s.add(Double.parseDouble(tmp));
				i++;
			}else{
				switch (express.charAt(i)) {
				case '+':
					a=s.pop();//System.out.println("a="+a);
					b=s.pop();//System.out.println("b="+b);
					c=b+a;    //System.out.println("c="+c);
					s.add(c);
					break;
				case '-':
					a=s.pop();//System.out.println("a="+a);
					b=s.pop();//System.out.println("b="+b);
					c=b-a;	  //System.out.println("c="+c);
					s.add(c);
					break;
				case '*':
					a=s.pop();//System.out.println("a="+a);
					b=s.pop();//System.out.println("b="+b);
					c=b*a;	  //System.out.println("c="+c);
					s.add(c);
					break;
				case '/':
					a=s.pop();//System.out.println("a="+a);
					b=s.pop();//System.out.println("b="+b);
					c=b/a;	  //System.out.println("c="+c);
					s.add(c);
					break;
				default:
					break;
				}
				i++;
			}
		}
		return 0;
	}
}
