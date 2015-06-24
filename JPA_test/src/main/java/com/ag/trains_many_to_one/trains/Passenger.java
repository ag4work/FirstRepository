package com.ag.trains_many_to_one.trains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey on 20.06.2015.
 */
@Entity
@Table(name="passenger")
public class Passenger {

    @Id
    @Column(name = "passenger_id")
    @GeneratedValue
    private int passengerId;

    @OneToMany (mappedBy = "passenger", cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<Ticket>();

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    private String name;


    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", name='" + name + '\'' +
                '}';
    }

    @Basic
    private String basic;

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }
}
