package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMe {
	public static void main(String[] args) {
		Student stud1 = new Student();
		stud1.setId(1);
		stud1.setName("Nits");
		College col = new College(stud1,"IT");
		col.getInfo(stud1);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Student stud2 = context.getBean("stud", Student.class);
		College col2 = context.getBean("col", College.class);
		col2.getInfo(stud2);
		
		Student stud3 = context.getBean("stud", Student.class);
		College col1 = context.getBean("col", College.class);
		col2.getInfo(stud2);
		
		System.out.println(stud3==stud2);
		
	}

}
