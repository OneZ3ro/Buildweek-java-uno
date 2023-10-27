package entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "controlli_tratte")
@NamedQueries({@NamedQuery(name = "getTempiEffettivi", query = "SELECT c FROM ControlloTratta c WHERE c.mezzoDiTrasporto=:idMezzo")})
public class ControlloTratta {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private MezzoDiTrasporto mezzoDiTrasporto;
    @JoinColumn(name = "tempo_effettivo")
    private long tempoEffettivo;

    public ControlloTratta() {
    }

    public ControlloTratta(MezzoDiTrasporto mezzoDiTrasporto, long tempoEffettivo) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.tempoEffettivo = tempoEffettivo;

    }

    @Override
    public String toString() {
        return "ControlloTratta{" +
                "id=" + id +
                ", mezzoDiTrasporto=" + mezzoDiTrasporto +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public long getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(long tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }
}
