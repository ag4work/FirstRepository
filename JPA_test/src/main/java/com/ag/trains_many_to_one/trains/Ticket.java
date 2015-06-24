package com.ag.trains_many_to_one.trains;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alexey on 20.06.2015.
 */
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue
    private int ticketId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

//    @Column(name = "passenger_id")
//    private int passenger;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Column(name = "train_id")
    private int trainId;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", date=" + date +
                ", passenger=" + passenger +
                ", trainId=" + trainId +
                '}';
    }
}
