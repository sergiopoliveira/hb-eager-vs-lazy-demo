package com.sergio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sergio.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save Java object
		try {

			int studentId = 1;

			// now get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);

			// delete the student
		//	System.out.println("Deleting student: " + myStudent);
		//	session.delete(myStudent);

			// delete student id=2
			session
				.createQuery("delete from Student WHERE id=2")
				.executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
		
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
