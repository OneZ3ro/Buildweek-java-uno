package entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue
    @Column(name = "tratta_id")
    private UUID trattaID;
    private String partenza;
    private String destinazione;
    @Column(name = "tempo_medio")
    private long tempoMedio;

    @OneToMany(mappedBy = "tratta")
    private List<MezzoDiTrasporto> trasporti;


    public Tratta() {
    }

    public Tratta(String partenza, String destinazione, long tempoMedio) {
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.tempoMedio = tempoMedio;
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

    public long getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(long tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    @Override
    public String toString() {
        return "Tratta= " +
                "Id: " + trattaID +
                ", Partenza: " + partenza +
                ", Destinazione: " + destinazione;
    }
}
