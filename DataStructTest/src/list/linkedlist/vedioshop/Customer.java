package list.linkedlist.vedioshop;


public class Customer {
	private String firstName;
	private String lastName;
	private String id;
	private VedioList hiredVedios=new VedioList();
	public Customer(){
		hiredVedios=new VedioList();
	}
	public Customer(String firstName,String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
		this.id=null;	
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void printId(){
		System.out.println(lastName+"'s id:"+id);
	}
	public VedioList getHiredVedios() {
		return hiredVedios;
	}
	public void setHiredVedios(Vedio vedio) {
		hiredVedios.addVedio(vedio);
	}
	public void hireVedio(Vedio vedio){
		if(hiredVedios.isVedioAvailable(vedio.getMovieName()))
			hiredVedios.checkOut(vedio);
	}
	public void paybackVedio(Vedio vedio){
		hiredVedios.checkIn(vedio);
	}
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName
				+ ", id=" + id + ", hiredVedios=" + hiredVedios + "]";
	}
	public void printHiredVedios(){
		hiredVedios.print();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
}
