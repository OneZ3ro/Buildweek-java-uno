package entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MezzoDiTrasporto {
    @Id
    @GeneratedValue
    @Column(name = "mezzo_di_trasporto_id")
    private UUID mezzoDiTrasportoId;
    @Enumerated(EnumType.STRING)

    private MezzoDiTrasporto mezzoDiTrasporto;
    private long capienza;
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    public MezzoDiTrasporto(){}

    public MezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto, long capienza, Tratta tratta) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.capienza = capienza;
        this.tratta = tratta;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public void setMezzoDiTrasporto(MezzoDiTrasporto mezzoDiTrasporto) {
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public long getCapienza() {
        return capienza;
    }

    public void setCapienza(long capienza) {
        this.capienza = capienza;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public UUID getMezzoDiTrasportoId() {
        return mezzoDiTrasportoId;
    }

    @Override
    public String toString() {
        return "MezzoDiTrasporto{" +
                "mezzoDiTrasportoId=" + mezzoDiTrasportoId +
                ", mezzoDiTrasporto=" + mezzoDiTrasporto +
                ", capienza=" + capienza +
                ", tratta=" + tratta +
                '}';
    }
}
