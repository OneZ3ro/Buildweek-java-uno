package entities;

import enums.TipoAbbonamento;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Tessera extends DocumentoVendita {
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;
    private LocalDate dataDiScadenza;
    private LocalDate dataDiRinnovo;
    private boolean active;

    public Tessera(){}

    public Tessera(LocalDate dataDiRilascio, Utente utente, TipoAbbonamento tipoAbbonamento, LocalDate dataDiRinnovo) {
        super(dataDiRilascio);
        this.utente = utente;
        this.tipoAbbonamento = tipoAbbonamento;
        this.dataDiScadenza = dataDiRinnovo.plusYears(1);
        this.dataDiRinnovo = dataDiRinnovo;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    public LocalDate getDataDiRinnovo() {
        return dataDiRinnovo;
    }

    public void setDataDiRinnovo(LocalDate dataDiRinnovo) {
        this.dataDiRinnovo = dataDiRinnovo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Utente getUtente() {
        return utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "utente=" + utente +
                ", tipoAbbonamento=" + tipoAbbonamento +
                ", dataDiScadenza=" + dataDiScadenza +
                ", dataDiRinnovo=" + dataDiRinnovo +
                ", active=" + active +
                '}';
    }
}
