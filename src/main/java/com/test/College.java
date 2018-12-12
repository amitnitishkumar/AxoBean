package com.test;

public class College {	
	public College(Student student, String dept) {		
		this.student = student;
		this.dept = dept;
	}
	Student student;
	String dept;
	public void getInfo(Student stud){
		System.out.println(student.getName()+" studies in "+dept);
	}
	

}
