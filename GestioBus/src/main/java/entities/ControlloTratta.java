package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "controlli_tratte")
public class ControlloTratta {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;
    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private MezzoDiTrasporto mezzoDiTrasporto;

    private LocalDate partenzaData;
    private LocalDate arrivoData;
    private String orarioPartenza;
    private String orarioArrivo;

    public ControlloTratta() {
    }

    public ControlloTratta(Tratta tratta, MezzoDiTrasporto mezzoDiTrasporto) {
        LocalDateTime t = LocalDateTime.now();
        String s = t.getHour() + ":" + t.getMinute();
        this.tratta = tratta;
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.partenzaData = LocalDate.now();
        this.orarioPartenza = s;

    }

    //METODI PER L'AUTISTA PER SETTARE DATA E ORA DI PARTENZA E DI ARRIVO, CONSIDERATI LOCALEDATETIME
    public ControlloTratta start(Tratta t, MezzoDiTrasporto v) {
        return new ControlloTratta(t, v);
    }

    public LocalDate getArrivoData() {
        return arrivoData;
    }

    public void setArrivoData(LocalDate arrivoData) {
        this.arrivoData = arrivoData;
    }

    public void end(ControlloTratta c) {
        LocalDateTime t = LocalDateTime.now();
        String s = t.getHour() + ":" + t.getMinute();
        c.setOrarioArrivo(s);
        c.setArrivoData(LocalDate.now());
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public LocalDate getPartenzaData() {
        return partenzaData;
    }

    public void setPartenzaData(LocalDate partenzaData) {
        this.partenzaData = partenzaData;
    }

    public String getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(String orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }
}
