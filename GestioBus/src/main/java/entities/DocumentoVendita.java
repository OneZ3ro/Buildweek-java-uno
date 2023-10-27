package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "doc_vendita")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class DocumentoVendita {
    @Id
    @GeneratedValue
    @Column(name = "doc_vendita_id")
    private UUID documentoVenditaId;
    @Column(name = "data_ril")
    private LocalDate dataDiRilascio;

    @ManyToOne
    @JoinColumn(name = "pv_id")
    private PuntoVendita puntoVendita;

    public DocumentoVendita() {
    }

    public DocumentoVendita(LocalDate dataDiRilascio, PuntoVendita puntoVendita) {
        this.dataDiRilascio = dataDiRilascio;
        this.puntoVendita = puntoVendita;
    }

    public UUID getDocumentoVenditaId() {
        return documentoVenditaId;
    }

    public void setDocumentoVenditaId(UUID documentoVenditaId) {
        this.documentoVenditaId = documentoVenditaId;
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
