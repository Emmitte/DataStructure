package list.linkedlist.vedioshop;

import java.util.ArrayList;
import java.util.Iterator;

public class VedioList {
	private ArrayList<Vedio> vedios=new ArrayList<Vedio>();
	public VedioList(){
		
	}
	public void addVedio(Vedio vedio){
		vedios.add(vedio);
	}
	public void print(){
		System.out.println("movie list:");
		Iterator<Vedio> iter=vedios.iterator();
		Vedio v;
		while(iter.hasNext()){
			v=iter.next();
			System.out.println(v.toString());
		}
	}
	public boolean check(Vedio vedio) {
		for(int i=0;i<vedios.size();i++){
			if(vedios.get(i).equals(vedio))
				return true;
		}
		return false;
	}
	public boolean isVedioAvailable(String movieName){
		Iterator<Vedio> iter=vedios.iterator();
		Vedio v;
		while(iter.hasNext()){
			v=iter.next();
			if(v.getMovieName().equals(movieName)&&v.isCanHire())
				return true;
		}
		return false;
	}
	public void checkIn(Vedio vedio) {
		for(int i=0;i<vedios.size();i++){
			if(vedios.get(i).equals(vedio)){
				vedio.payback();
				vedios.set(i, vedio);
				break;
			}
		}
	}
	public void checkOut(Vedio vedio){
		for(int i=0;i<vedios.size();i++){
			if(vedios.get(i).equals(vedio)){
				vedio.hireMovie();
				vedios.set(i, vedio);
				break;
			}
		}
	}
}
