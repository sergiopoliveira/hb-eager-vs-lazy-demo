package com.sergio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sergio.hibernate.demo.entity.Instructor;
import com.sergio.hibernate.demo.entity.InstructorDetail;
import com.sergio.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		// use the session object to save Java object
		try {
			
			// create a student object
			System.out.println("Creating a new student object...");
			
//			//associate the objects
//			Instructor tempInstructor = 
//					new Instructor("Sergio", "Oliveira", "sergio@sergio.com");
//			
//			InstructorDetail tempInstructorDetail = 
//					new InstructorDetail("http://youtube.com/sergio", "Code");
			
			Instructor tempInstructor = 
					new Instructor("Alberto", "Peneda", "alberto@peneda.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("http://youtube.com/alberto", "Study");
  	
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the object
			System.out.println("Saving the object(s)...");
			System.out.println(tempInstructor);
			//NOTE: this will also save the deatails object
			//because of CascadeType.ALL
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}

}
