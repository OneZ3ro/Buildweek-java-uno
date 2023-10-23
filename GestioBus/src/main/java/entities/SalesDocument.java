package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity

public abstract class SalesDocument {
    @Id
    @GeneratedValue
    private UUID salesDocumentId;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private SailPoint sailPoint;
    private Route route;

    public SalesDocument(){};
}
