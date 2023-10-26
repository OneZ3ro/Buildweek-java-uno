package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Biglietto extends DocumentoVendita{
    private LocalDate dataDiConvalidazione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private MezzoDiTrasporto mezzoDiTrasporto;

    public Biglietto(){}

    public Biglietto(LocalDate dataDiRilascio, PuntoVendita puntoVendita, LocalDate dataDiConvalidazione, MezzoDiTrasporto mezzoDiTrasporto) {
        super(dataDiRilascio, puntoVendita);
        this.dataDiConvalidazione = dataDiConvalidazione;
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }

    public LocalDate getDataDiConvalidazione() {
        return dataDiConvalidazione;
    }

    public void setDataDiConvalidazione(LocalDate dataDiConvalidazione) {
        this.dataDiConvalidazione = dataDiConvalidazione;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "dataDiConvalidazione=" + dataDiConvalidazione +
                ", mezzoDiTrasporto=" + mezzoDiTrasporto +
                '}';
    }
}
