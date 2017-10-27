package stack;

import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;

public class test {

	public static void main(String[] args) {
		Stack<Student> s = new Stack<Student>();
		Student s1=new Student("aaa", 3.1);
		Student s2=new Student("bbb", 3.5);
		Student s3=new Student("ccc", 3.2);
		Student s4=new Student("ddd", 4.0);
		s.push(s1);
		s.push(s2);
		s.push(s3);
		s.push(s4);
		Iterator<Student> iter=s.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.println();
		System.out.println(s.pop());
		System.out.println();
		iter=s.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.println();
		System.out.println(s.pop());
		System.out.println();
		iter=s.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		TreeSet<Student> tree=new TreeSet<Student>();
		tree.add(s1);
		tree.add(s2);
		tree.add(s3);
		tree.add(s4);
		iter=tree.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}

}
