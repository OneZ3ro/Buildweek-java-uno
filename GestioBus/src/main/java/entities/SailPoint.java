package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public abstract class SailPoint {
    @Id
    @GeneratedValue
    @Column(name = "sail_point_id")
    private UUID sailPointId;
    @Column(name = "total_tickets_sold")
    private long totalTicketsSold;
    @Column(name = "date_tickets_sold")
    private LocalDate dateTicketSold;
    private String city;
    private String address;

    public SailPoint(){};
}
