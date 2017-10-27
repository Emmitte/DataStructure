package list.arraylist.polynomial;

import java.util.ArrayList;
import java.util.Iterator;

public class test {
	
	static ArrayList<Polynomial> px1=new ArrayList<Polynomial>();
	static ArrayList<Polynomial> px2=new ArrayList<Polynomial>();
	
	public static void show(ArrayList<Polynomial> px){
		for(int i=0;i<px.size();i++){
			Polynomial p=px.get(i);
			if(i==0&&p.getIndex()==0)
				System.out.print(p.getCoefficient());
			else if(i==0&&p.getIndex()!=0)
				System.out.print(p.toString());
			else
				System.out.print("+"+p.toString());
		}
		System.out.println();
	}
	
	public static void main(String[] args) {	
		Polynomial p=new Polynomial(1, 0);
		px1.add(p);
		p=new Polynomial(2, 1);
		px1.add(p);
		p=new Polynomial(3, 3);
		px1.add(p);
		System.out.println("px1:");
		show(px1);
		p=new Polynomial(1, 1);
		px2.add(p);
		p=new Polynomial(4, 2);
		px2.add(p);
		p=new Polynomial(6, 7);
		px2.add(p);
		System.out.println("px2:");
		show(px2);
		Calculate cal=new Calculate();
		ArrayList<Polynomial> result;
		result=cal.add(px1, px2);
		System.out.println("px1 + px2 result:");
		show(result);
		result.clear();
		result=cal.sub(px2, px1);
		System.out.println("px1 - px2 result:");
		show(result);
		result.clear();
		result=cal.mul(px2, px1);
		System.out.println("px1 * px2 result:");
		show(result);
	}

}
