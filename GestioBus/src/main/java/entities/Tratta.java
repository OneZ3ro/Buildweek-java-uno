package entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Tratta {
    @Id
    @GeneratedValue
    @Column(name = "tratta_id")
    private UUID trattaID;
    private String partenza;
    private String destinazione;

    @OneToMany(mappedBy = "tratta")
    private List<MezzoDiTrasporto> trasporti;
    @OneToMany(mappedBy = "tratta")
    private Set<ControlloTratta> checks;

    public Tratta() {
    }

    public Tratta(String partenza, String destinazione) {
        this.partenza = partenza;
        this.destinazione = destinazione;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public UUID getTrattaID() {
        return trattaID;
    }

    public List<MezzoDiTrasporto> getTrasporti() {
        return trasporti;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "trattaID=" + trattaID +
                ", partenza='" + partenza + '\'' +
                ", destinazione='" + destinazione + '\'' +
                '}';
    }
}
