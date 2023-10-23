package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Route {
    @Id
    @GeneratedValue
    @Column(name = "route_id")
    private UUID routeId;
    private Transport transport;
    private SalesDocument salesDocument;
    private String departure;
    private String arrival;
    private double avaregeTime;
    public double effectiveTime;

    public Route(){};
}
