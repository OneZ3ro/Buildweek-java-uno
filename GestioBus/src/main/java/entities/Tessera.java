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
    private LocalDate dataDiEmissione;
    private boolean active;

    public Tessera(){}

    public Tessera(LocalDate dataDiRilascio, Utente utente, TipoAbbonamento tipoAbbonamento, LocalDate dataDiEmissione, boolean active) {
        super(dataDiRilascio);
        this.utente = utente;
        this.tipoAbbonamento = tipoAbbonamento;
        this.dataDiScadenza = dataDiEmissione.plusYears(1);
        this.dataDiEmissione = dataDiEmissione;
        this.active = active;
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

    public LocalDate getDataDiEmissione() {
        return dataDiEmissione;
    }

    public void setDataDiEmissione(LocalDate dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
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
                ", dataDiEmissione=" + dataDiEmissione +
                ", active=" + active +
                '}';
    }
}
