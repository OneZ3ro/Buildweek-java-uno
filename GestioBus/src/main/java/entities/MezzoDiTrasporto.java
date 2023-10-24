package entities;

import enums.Stato;
import enums.TipoDiMezzo;

import javax.persistence.*;
import java.util.List;
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
    @Enumerated(EnumType.STRING)
    private Stato stato;
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private List<Manutenzione> manutenzioni;

    public MezzoDiTrasporto() {
    }

    public MezzoDiTrasporto(TipoDiMezzo tipoDiMezzo, long capienza, Tratta tratta, Stato stato) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
        this.tratta = tratta;
        this.stato = stato;
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

    public Stato getStato() {
        return stato;
    }

    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

    @Override
    public String toString() {
        return "MezzoDiTrasporto{" +
                "mezzoDiTrasportoId=" + mezzoDiTrasportoId +
                ", tipoDiMezzo=" + tipoDiMezzo +
                ", capienza=" + capienza +
                ", stato=" + stato +
                ", tratta=" + tratta +
                ", manutenzioni=" + manutenzioni +
                '}';
    }
}
