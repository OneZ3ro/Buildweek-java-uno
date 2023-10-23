package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;
    private String name;
    private String surname;
    private Card card;

    public User(){};
}
