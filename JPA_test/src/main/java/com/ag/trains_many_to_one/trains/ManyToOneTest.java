package com.ag.trains_many_to_one.trains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by Alexey on 20.06.2015.
 */
public class ManyToOneTest {
    static EntityManagerFactory  emf = Persistence.createEntityManagerFactory("TrainPU");
    static EntityManager         em  = emf.createEntityManager();

    public static void main(String[] args) {

        //Ticket ticket = em.find(Ticket.class, 15);
//        createPassengerAndTickets();

        em.getTransaction().begin();
        //em.remove(em.find(Passenger.class,13));
      //  em.remove(em.find(Ticket.class,1));
        Passenger passengerMaryl =  em.find(Passenger.class, 6);
        Ticket someTicket = createTicket(new Date(), 1, passengerMaryl);
        passengerMaryl.getTickets().add(someTicket);
        em.getTransaction().commit();

//        System.out.println(em.find(Passenger.class, 13));
//        System.out.println(em.find(Passenger.class, 13).getTickets());


        em.close();
        emf.close();
    }

    static void createPassengerAndTickets(){
        Passenger passenger = createPassenger("Женя");
        putToDB(passenger);
        System.out.println(passenger);

        Ticket ticket1 = createTicket(new Date(), 1, passenger);
        Ticket ticket2 = createTicket(new Date(), 2, passenger);

        putTicketToDB(ticket1);
        putTicketToDB(ticket2);
    }

    static void putToDB(Passenger o){
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    static void putTicketToDB(Ticket o){
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
    }

    static Passenger createPassenger(String name){
        Passenger passenger = new Passenger();
        passenger.setName(name);
        return passenger;
    }

    static Ticket createTicket(Date date, int trainId, Passenger passenger){
        Ticket ticket = new Ticket();
        ticket.setDate(date);
        ticket.setPassenger(passenger);
        ticket.setTrainId(trainId);
        return ticket;
    }




}
