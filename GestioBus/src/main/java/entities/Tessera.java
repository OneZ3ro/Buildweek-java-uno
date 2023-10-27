package entities;

import enums.TipoAbbonamento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tessere")
public class Tessera extends DocumentoVendita {
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @Column(name = "tipo_abb")
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;
    @Column(name = "data_scad")
    private LocalDate dataDiScadenza;
    @Column(name = "data_rinn")
    private LocalDate dataDiRinnovo;
    private boolean active;

    public Tessera() {
    }

    public Tessera(LocalDate dataDiRilascio, PuntoVendita puntoVendita, Utente utente, TipoAbbonamento tipoAbbonamento,
                   LocalDate dataDiRinnovo) {
        super(dataDiRilascio, puntoVendita);
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
                // "utente=" + utente.getNome() +
                ", tipoAbbonamento=" + tipoAbbonamento +
                ", dataDiScadenza=" + dataDiScadenza +
                ", dataDiRinnovo=" + dataDiRinnovo +
                '}';
    }
}
