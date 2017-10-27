package list.linkedlist.vedioshop;

import java.util.Scanner;

public class test {
	VedioList vedios=new VedioList();
	CustomerList customers=new CustomerList();
	Vedio vedio;
	Customer customer;
	public void createVediolist(){
		vedio=new Vedio("Avatar", "Sam Worthington", "Jon Landau", "James Cameron", "Twentieth (20th) Century Fox Film Corporation", 10);
		vedios.addVedio(vedio);
		vedio=new Vedio("Avatar2", "Sam Worthington", "Jon Landau", "James Cameron", "Twentieth (20th) Century Fox Film Corporation", 20);
		vedios.addVedio(vedio);
		vedio=new Vedio("aaa", "bbb", "ccc", "ddd", "eee", 10);
		vedios.addVedio(vedio);
	}
	public Vedio makeVedio(){
		String movieName;
		String starName;
		String producerName;
		String directorName;
		String companyName;
		int copyCount=1;
		Scanner sc=new Scanner(System.in);
		System.out.println("input movieName:");
		movieName=sc.nextLine();
		System.out.println("input starName:");
		starName=sc.nextLine();
		System.out.println("input producerName:");
		producerName=sc.nextLine();
		System.out.println("input directorName:");
		directorName=sc.nextLine();
		System.out.println("input companyName:");
		companyName=sc.nextLine();
		vedio=new Vedio(movieName, starName, producerName, directorName, companyName, copyCount);
		return vedio;
	}
	public void register(){
		System.out.println("please regist your infomation");
		Scanner sc=new Scanner(System.in);
		customers.printCustomer();
		String firstName,lastName;
		System.out.println("your firstName:");
		firstName=sc.nextLine();
		System.out.println("your lastName:");
		lastName=sc.nextLine();
		customer=new Customer(firstName, lastName);
		customers.addCustomer(customer);
	}
	public boolean login(){
		System.out.println("welcome to vedio shop");
		System.out.println("please login first");
		Scanner sc=new Scanner(System.in);
		String firstName,lastName;
		System.out.println("your firstName:");
		firstName=sc.nextLine();
		System.out.println("your lastName:");
		lastName=sc.nextLine();
		customer=new Customer(firstName, lastName);
		System.out.println(customers.check(customer));
		if(customers.check(customer))
			return true;
		else
			return false;
	}
	public void displayMenu(){
		System.out.println("Select one of the following.");
		System.out.println("1:To check whether this vedio in the store.");
		System.out.println("2:To hire a vedio.");
		System.out.println("3:To pay back a vedio.");
		System.out.println("4:To show the vedios in the store.");
		System.out.println("5:To add my vedio list.");
		System.out.println("6:To print my history.");
		System.out.println("7:To exit.");
	}
	public void execute(){
		while(true){
			displayMenu();
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			if(choice==7)
				break;
			execute(choice);
		}
	}
	public void inserUserVedioList(){
		
	}
	private void execute(int choice){
		switch (choice) {
		case 1:
			Scanner sc=new Scanner(System.in);
			System.out.println("please input the movieName");
			String movieName=sc.nextLine();
			if(vedios.isVedioAvailable(movieName))
				System.out.println("it can be hired.");
			else
				System.out.println("it cannot be hired.");
			break;
		case 2:vedios.checkOut(makeVedio());
			break;
		case 3:vedios.checkIn(makeVedio());
			break;
		case 4:vedios.print();
			break;
		case 5:customer.setHiredVedios(vedio);
			break;
		case 6:customer.printHiredVedios();
			break;
		default:
			break;
		}
	}
	public static void main(String[] args) {
		test t=new test();
		t.createVediolist();
		while(true){
			if(t.login()==true)
				break;
			System.out.println("login failed!");
			t.register();
		}
		System.out.println("login success!");
		t.execute();
	}
}
