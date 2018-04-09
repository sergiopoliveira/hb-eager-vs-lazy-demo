package com.sergio.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sergio.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {
			
	
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			//query students: lastName='Doe'
			System.out.println("\n\nStudents who have last name of Doe");
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			//query students: lastName='Doe' OR firstName='Daffy'
			System.out.println("\n\nStudents who have last name Doe or first name Daffy");
			theStudents = 
					session.createQuery("from Student s WHERE s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			//query students: where email LIKE '%sergio.com'
			System.out.println("\n\nStudents whose email ends with sergio.com");
			theStudents = 
					session.createQuery("from Student s WHERE s.email LIKE '%sergio.com'")
					.getResultList();
			
			// display the students
			displayStudents(theStudents);
						
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
