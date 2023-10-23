package entities;

import entities.enums.State;
import entities.enums.TypeVehicle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public abstract class Transport {
    @Id
    @GeneratedValue
    private UUID transportId;
    private TypeVehicle typeVehicle;
    @Column(name = "seating_capacity")
    private long seatingCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
    private List<HistoricVehicle> historicVehicles;
    private Route route;
    public Transport(){}
}
