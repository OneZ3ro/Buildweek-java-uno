package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Utente {
    @Id
    @GeneratedValue
    @Column(name = "utente_id")
    private UUID utenteId;

    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    public Utente() {}

    public Utente(String nome, String cognome, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public UUID getUtenteId() {
        return utenteId;
    }

    public Tessera getTessera() {
        return tessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "utenteId=" + utenteId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                ", tessera=" + tessera +
                '}';
    }
}
