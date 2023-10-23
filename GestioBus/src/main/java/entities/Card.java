package entities;

import entities.enums.Frenquency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Card extends SalesDocument{
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    private Frenquency frenquency;
    private boolean activate;

    private User user;

    public Card(){};
}
