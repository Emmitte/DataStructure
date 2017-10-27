package list.arraylist.polynomial;

import java.util.ArrayList;

public class Calculate {
	ArrayList<Polynomial> px=new ArrayList<Polynomial>();
	Polynomial p;
	Polynomial p1,p2;
	int coefficient;
	int index;
	public Calculate(){
		
	}
	public ArrayList<Polynomial> add(ArrayList<Polynomial> px1,ArrayList<Polynomial> px2){	
		int i=0;
		int j=0;
		while(i<px1.size()&&j<px2.size()){			
			p1=px1.get(i);
			p2=px2.get(j);
			if(p1.getIndex()==p2.getIndex()){
				coefficient=p1.getCoefficient()+p2.getCoefficient();
				index=p1.getIndex();
				i++;
				j++;
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
			if(p1.getIndex()<p2.getIndex()){
				coefficient=p1.getCoefficient();
				index=p1.getIndex();
				i++;
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
			if(p1.getIndex()>p2.getIndex()){
				coefficient=p2.getCoefficient();
				index=p2.getIndex();
				j++;
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
		}
		while(i<px1.size()){
			coefficient=p1.getCoefficient();
			index=p1.getIndex();
			i++;
			p=new Polynomial(coefficient, index);
			px.add(p);
		}
		while(j<px2.size()){
			coefficient=p2.getCoefficient();
			index=p2.getIndex();
			j++;
			p=new Polynomial(coefficient, index);
			px.add(p);
		}
		return px;
	}
	public ArrayList<Polynomial> sub(ArrayList<Polynomial> px1,ArrayList<Polynomial> px2){
		int i=0;
		int j=0;
		while(i<px1.size()&&j<px2.size()){
			p1=px1.get(i);
			p2=px2.get(j);
			if(p1.getIndex()==p2.getIndex()){
				coefficient=p1.getCoefficient()-p2.getCoefficient();
				index=p1.getIndex();
				i++;
				j++;
				p=new Polynomial(Math.abs(coefficient), index);
				px.add(p);
			}
			if(p1.getIndex()<p2.getIndex()){
				coefficient=p1.getCoefficient();
				index=p1.getIndex();
				i++;
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
			if(p1.getIndex()>p2.getIndex()){
				coefficient=p2.getCoefficient();
				index=p2.getIndex();
				j++;
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
		}
		while(i<px1.size()){
			coefficient=p1.getCoefficient();
			index=p1.getIndex();
			i++;
			p=new Polynomial(coefficient, index);
			px.add(p);
		}
		while(j<px2.size()){
			coefficient=p2.getCoefficient();
			index=p2.getIndex();
			j++;
			p=new Polynomial(coefficient, index);
			px.add(p);
		}
		return px;
	}
	public ArrayList<Polynomial> mul(ArrayList<Polynomial> px1,ArrayList<Polynomial> px2){
		int i=0;
		int j=0;
		while(i<px1.size()){
			p1=px1.get(i);
			for(j=0;j<px2.size();j++){
				p2=px2.get(j);
				coefficient=p1.getCoefficient()*p2.getCoefficient();
				index=p1.getIndex()+p2.getIndex();
				p=new Polynomial(coefficient, index);
				px.add(p);
			}
			i++;
		}
		for(i=0;i<px.size();i++){
			p1=px.get(i);
			for(j=i+1;j<px.size();j++){
				p2=px.get(j);
				if(p1.getIndex()==p2.getIndex()){
					coefficient=p1.getCoefficient()+p2.getCoefficient();
					index=p1.getIndex();
					p=new Polynomial(coefficient, index);
					px.set(i, p);
					px.remove(j);
					break;
				}
			}
		}
		return px;
	}
}
