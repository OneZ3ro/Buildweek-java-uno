package entities;

import entities.enums.State;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

public class HistoricVehicle {
    @Id
    @GeneratedValue
    @Column(name = "historic_vehicle_id")
    private UUID historicVehicleId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private Transport transport;

    public HistoricVehicle(){};
}
