package entities;

import entities.enums.State;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class TicketMachine extends SailPoint {
    @Enumerated(EnumType.STRING)
    private State state;
    private TicketMachine(){};
}
