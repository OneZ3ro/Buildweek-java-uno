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

    @ManyToMany
    @JoinTable(name = "puntovendita_documentovendita", joinColumns = @JoinColumn(name = "documento_vendita_id"), inverseJoinColumns = @JoinColumn(name = "punto_vendita_id"))
    private List<PuntoVendita> listaPuntiVendita;

    public DocumentoVendita(){};
    public DocumentoVendita(UUID documentoVenditaId, LocalDate dataDiRilascio) {
        this.documentoVenditaId = documentoVenditaId;
        this.dataDiRilascio = dataDiRilascio;
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

    public List<PuntoVendita> getListaPuntiVendita() {
        return listaPuntiVendita;
    }

    public void setListaPuntiVendita(List<PuntoVendita> listaPuntiVendita) {
        this.listaPuntiVendita = listaPuntiVendita;
    }

    @Override
    public String toString() {
        return "DocumentoVendita{" +
                "documentoVenditaId=" + documentoVenditaId +
                ", dataDiRilascio=" + dataDiRilascio +
                ", listaPuntiVendita=" + listaPuntiVendita +
                '}';
    }
}
