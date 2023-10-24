package entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue
    @Column(name = "punto_vendita_id")
    private UUID puntoVenditaId;
    private String nome;
    private String citta;
    @ManyToMany
    @JoinTable(name = "puntovendita_documentovendita", joinColumns = @JoinColumn(name = "punto_vendita_id"), inverseJoinColumns = @JoinColumn(name = "documento_vendita_id"))
    private List<DocumentoVendita> documentoVendite;

    public PuntoVendita() {
    }

    public PuntoVendita(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public UUID getPuntoVenditaId() {
        return puntoVenditaId;
    }

    public List<DocumentoVendita> getListaPuntiVendita() {
        return documentoVendite;
    }

    @Override
    public String toString() {
        return "PuntoVendita{" +
                "puntoVenditaId=" + puntoVenditaId +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                ", listaPuntiVendita=" + documentoVendite +
                '}';
    }
}
