package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {
    @Id
    @GeneratedValue
    @Column(name = "manut_id")
    private UUID manutenzioneId;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    private LocalDate dataFine;

    private String descrizione;
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private MezzoDiTrasporto mezzoDiTrasporto;

    public Manutenzione() {
    }

    public Manutenzione(LocalDate dataInizio, LocalDate dataFine, MezzoDiTrasporto mezzoDiTrasporto, String descrizione) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.descrizione = descrizione;
    }

    public Manutenzione(MezzoDiTrasporto mezzoDiTrasporto, String descrizione) {
        this.dataInizio = LocalDate.now();
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

    public void setDataFine() {
        this.dataFine = LocalDate.now();
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
