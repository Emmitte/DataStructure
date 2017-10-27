package list.linkedlist.vedioshop;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomerList {
	private ArrayList<Customer> customers=new ArrayList<Customer>();
	
	public CustomerList(){
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	public void printCustomer(){
		System.out.println("movie list:");
		Iterator<Customer> iter=customers.iterator();
		Customer c;
		while(iter.hasNext()){
			c=iter.next();
			System.out.println(c.toString());
		}
	}
	public boolean check(Customer customer){
		for(int i=0;i<customers.size();i++){
			if(customers.get(i).equals(customer))
				return true;
		}
		return false;
	}
}
