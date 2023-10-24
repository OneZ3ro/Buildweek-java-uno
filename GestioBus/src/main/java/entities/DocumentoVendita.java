package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DocumentoVendita {
    @Id
    @GeneratedValue
    @Column(name = "documento_vendita_id")
    private UUID documentoVenditaId;
    private LocalDate dataDiRilascio;

    @ManyToOne
    @JoinColumn(name = "punto_vendita_id")
    private PuntoVendita puntoVendita;

    public DocumentoVendita() {
    }

    public void setDocumentoVenditaId(UUID documentoVenditaId) {
        this.documentoVenditaId = documentoVenditaId;
    }

    public DocumentoVendita(LocalDate dataDiRilascio, PuntoVendita puntoVendita) {
        this.dataDiRilascio = dataDiRilascio;
        this.puntoVendita = puntoVendita;
    }

    public UUID getDocumentoVenditaId() {
        return documentoVenditaId;
    }

    public LocalDate getDataDiRilascio() {
        return dataDiRilascio;
    }

    public void setDataDiRilascio(LocalDate dataDiRilascio) {
        this.dataDiRilascio = dataDiRilascio;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    @Override
    public String toString() {
        return "DocumentoVendita{" +
                "documentoVenditaId=" + documentoVenditaId +
                ", dataDiRilascio=" + dataDiRilascio +
                '}';
    }
}
