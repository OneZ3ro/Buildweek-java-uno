package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Manutenzione {
    @Id
    @GeneratedValue
    private UUID manutenzioneId;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    @ManyToOne
    @JoinColumn(name = "mezzo_di_trasporto_id")
    private MezzoDiTrasporto mezzoDiTrasporto;
    private String descrizione;

    public Manutenzione() {
    }

    public Manutenzione(LocalDate dataInizio, LocalDate dataFine, MezzoDiTrasporto mezzoDiTrasporto, String descrizione) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.descrizione = descrizione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public UUID getManutenzioneId() {
        return manutenzioneId;
    }

    public MezzoDiTrasporto getMezzoDiTrasporto() {
        return mezzoDiTrasporto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "manutenzioneId=" + manutenzioneId +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", mezzoDiTrasporto=" + mezzoDiTrasporto +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
