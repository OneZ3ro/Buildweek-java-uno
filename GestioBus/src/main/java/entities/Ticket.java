package entities;

import entities.enums.Frenquency;

import javax.persistence.Column;
import java.time.LocalDate;

public class Ticket extends SalesDocument{
    @Column(name = "convalidation_date")
    private LocalDate convalidationDate;
    private boolean convalidation;

    public Ticket(){};

}
