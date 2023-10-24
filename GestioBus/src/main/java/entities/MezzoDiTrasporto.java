package entities;

import enums.TipoDiMezzo;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MezzoDiTrasporto {
    @Id
    @GeneratedValue
    @Column(name = "mezzo_di_trasporto_id")
    private UUID mezzoDiTrasportoId;
    @Enumerated(EnumType.STRING)
    private TipoDiMezzo tipoDiMezzo;
    private long capienza;
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    public MezzoDiTrasporto(){}

    public MezzoDiTrasporto(TipoDiMezzo tipoDiMezzo, long capienza, Tratta tratta) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
        this.tratta = tratta;
    }

    public TipoDiMezzo getTipoDiMezzo() {
        return tipoDiMezzo;
    }

    public void setTipoDiMezzo(TipoDiMezzo tipoDiMezzo) {
        this.tipoDiMezzo = tipoDiMezzo;
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
                ", mezzoDiTrasporto=" + tipoDiMezzo +
                ", capienza=" + capienza +
                ", tratta=" + tratta +
                '}';
    }
}
