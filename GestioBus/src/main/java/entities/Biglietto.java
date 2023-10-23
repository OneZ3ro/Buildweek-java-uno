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
    private boolean convalidato;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private MezzoDiTrasporto mezzoDiTrasporto;

    public Biglietto(){}

    public Biglietto(UUID documentoVenditaId, LocalDate dataDiRilascio, LocalDate dataDiConvalidazione, boolean convalidato) {
        super(documentoVenditaId, dataDiRilascio);
        this.dataDiConvalidazione = dataDiConvalidazione;
        this.convalidato = convalidato;
    }

    public LocalDate getDataDiConvalidazione() {
        return dataDiConvalidazione;
    }

    public void setDataDiConvalidazione(LocalDate dataDiConvalidazione) {
        this.dataDiConvalidazione = dataDiConvalidazione;
    }

    public boolean isConvalidato() {
        return convalidato;
    }

    public void setConvalidato(boolean convalidato) {
        this.convalidato = convalidato;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "dataDiConvalidazione=" + dataDiConvalidazione +
                ", convalidato=" + convalidato +
                ", mezzoDiTrasporto=" + mezzoDiTrasporto +
                '}';
    }
}
