package com.ag.basic;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class CRUDExample {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SchoolPU");
        EntityManager em = emf.createEntityManager();

        // Find by id
        Schooler student = em.find(Schooler.class, 1L);
        System.out.printf("Student with id=1 is: %s\n",student);

        // Find non-existing user
        student = em.find(Schooler.class, 200L);
        System.out.printf("Student with id=200 is: %s\n\n", student);

        //Create
        Schooler newSchooler = createStudent();
        em.getTransaction().begin();
        em.persist(newSchooler);
        em.getTransaction().commit();
        System.out.printf("New student is: %s\n", em.find(Schooler.class, 150L));

        //Update
        newSchooler.setFirstName("Petya");
        em.getTransaction().begin();
        em.merge(newSchooler);
        em.getTransaction().commit();
        System.out.printf("Updated student is: %s\n\n", em.find(Schooler.class, 150L));

        //Delete
        em.getTransaction().begin();
        em.remove(newSchooler);
        em.getTransaction().commit();
        System.out.printf("Removed student is: %s\n", em.find(Schooler.class, 150L));
        em.close();
        emf.close();
    }

    private static Schooler createStudent(){
        Schooler newStudent = new Schooler();
        newStudent.setId(150);
        newStudent.setFirstName("John");
        newStudent.setLastName("Russel");
        newStudent.setBirthDate(new Date());
        newStudent.setSchoolId(1);
        return newStudent;
    }
}
