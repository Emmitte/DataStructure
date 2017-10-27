package stack.calculation;

import java.util.Stack;

public class Express {
	private String infix;
	private String postfix;
	private Stack<Character> s=new Stack<Character>(); 
	public Express(){
		this.infix=null;
		this.postfix=null;
	}
	
	public Express(String express){
		this.infix=express;
	}

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}

	public String getPostfix() {
		return postfix;
	}
	
	public void printPostfix(){
		System.out.println(infix+"-->"+postfix);
	}
	
	public void convertToPostfix(){
		postfix=null;
		int count1=0;
		char[] cs=infix.toCharArray();
		for(char c: cs){
			//System.out.println("infix[i]="+c);
			if(c==')'){
				//count1--;
				postfix+=s.pop();
				//System.out.println("count="+count1);
				//System.out.println("postfix="+postfix);
			}
			else if(c=='('){
				//count1++;
				//System.out.println("count="+count1);
			}
			else if(c=='+'||c=='-'||c=='*'||c=='/'){
				s.add(c);
				
			}else if(Character.isDigit(c)){
				if(postfix==null){
					//System.out.println("first="+infix);
					String tmp=String.valueOf(c);
					postfix=tmp;
				}else
					postfix+=c;
				//System.out.println("postfix="+postfix);
			}else{
				postfix+=s.pop();
				postfix+=c;
			}
		}
//		while(c!='\0'){
//			System.out.println("infix[i]="+c);
//			if(c==')'&&count1>0){
//				count1--;
//				if(infix==null){
//					String tmp2=String.valueOf(s.pop());
//					postfix=tmp2;
//				}
//				postfix+=s.pop();
//				System.out.println("postfix="+postfix);
//			}
//			else if(c=='('){
//				count1++;
//			}
//			else if(c=='+'||c=='-'||c=='*'||c=='/'){
//				s.add(c);
//				
//			}else{
//				if(postfix==null){
//					System.out.println("first="+infix);
//					String tmp=String.valueOf(c);
//					postfix=tmp;
//				}else
//					postfix+=c;
//				System.out.println("postfix="+postfix);
//			}
//			i++;
//		}
	}
	
	public boolean precedence(String sign1,String sign2){
		if((sign1.equals('*')||sign1.equals('/'))&&(sign1.equals('+')||sign1.equals('-')))
			return true;
		else 
			return false;
	}
}
