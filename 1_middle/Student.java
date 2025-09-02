package 박병우exam;

import java.util.*;

class Member {
	public String name, ID;

	public Member() {}
	public Member(String name, String iD) {
		super();
		this.name = name;
		ID = iD;
	}
	public void setName(String name) {	this.name = name;}
	public void setID(String iD) {ID = iD;}
	public String getName() {return name;}
	public String getID() {return ID;}
	public void printInfo() {
		System.out.println("Name = " + getName());
		System.out.println("ID = " + getID());
		System.out.println("------------------");
	}
}

class Student extends Member{
	private int tuition;

	public Student() {}
	public Student(String name, String ID, int tuition) {
		super();
		this.name = name;
		this.ID = ID;
		this.tuition = tuition;
	}
	public void setStudent(String name, String ID, int tuition) {this.name =name; this.ID =ID; this.tuition = tuition;}
	public String getStudentName() {return name;}
	public String getStudentID() {return ID;}
	public int getTuition() {return tuition;}
	public void printInfo()  {
		System.out.println("Name = " + getName());
		System.out.println("ID = " + getID());
		System.out.println("------------------");
		System.out.println("Tuition = " + getTuition());
		System.out.println("=====================");
}
	
	public static void main(String[] args) {
		Member m1 = new Member();
		m1.setName("Lee Sunsin");
		m1.setID("A1");
		m1.printInfo();
		Member m2 = new Member("Gang Gamchan", "A2");
		m2.printInfo();
		Student s1 = new Student();
		s1.setStudent("Uljimundeok", "A3", 3330000);
		s1.printInfo();
		Student s2 = new Student("Ondal", "A4", 4440000);
		s2.printInfo();
	}
}

