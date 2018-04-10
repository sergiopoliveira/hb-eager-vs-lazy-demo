package com.sergio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sergio.hibernate.demo.entity.Instructor;
import com.sergio.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			
			// start a transaction
			session.beginTransaction();
		
			// get instructor by primary key / id
			int theId = 1;
			Instructor tempInstructor = 
					session.get(Instructor.class, 1); //returns null if not found
			
			System.out.println(tempInstructor);
			
			//delete the instructors
			if(tempInstructor!=null) {
			
				//NOTE: will also delete the delete table because of CASCADE
				session.delete(tempInstructor);
			
			}
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} 
		finally {
			factory.close();
		}
	}

}
