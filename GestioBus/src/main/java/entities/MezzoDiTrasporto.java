package entities;

import enums.Stato;
import enums.TipoDiMezzo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mezzi")
public class MezzoDiTrasporto {
    @Id
    @GeneratedValue
    @Column(name = "mezzo_id")
    private UUID mezzoDiTrasportoId;
    @Column(name = "tipo_mezzo")
    @Enumerated(EnumType.STRING)
    private TipoDiMezzo tipoDiMezzo;
    private long capienza;
    @Enumerated(EnumType.STRING)
    private Stato stato;
    @Column(name = "conto_tratte")
    private int contoTratte;
    @Column(name = "data_immatricolazione")
    private LocalDate dataDiImmatricolazione;
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;

    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private List<Manutenzione> manutenzioni;

    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private List<Biglietto> bigliettoList;

    @OneToMany(mappedBy = "mezzoDiTrasporto")
    private Set<ControlloTratta> checks;


    public MezzoDiTrasporto() {
    }

    public MezzoDiTrasporto(TipoDiMezzo tipoDiMezzo, long capienza, Tratta tratta, Stato stato, int contoTratte, LocalDate dataDiImmatricolazione) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
        this.tratta = tratta;
        this.stato = stato;
        this.contoTratte = contoTratte;
        this.dataDiImmatricolazione = dataDiImmatricolazione;
    }

    public MezzoDiTrasporto(TipoDiMezzo tipoDiMezzo, long capienza, Tratta tratta) {
        this.tipoDiMezzo = tipoDiMezzo;
        this.capienza = capienza;
        this.tratta = tratta;
        this.stato = Stato.IN_SERVIZIO;
    }

    public TipoDiMezzo getTipoDiMezzo() {
        return tipoDiMezzo;
    }

    public void setTipoDiMezzo(TipoDiMezzo tipoDiMezzo) {
        this.tipoDiMezzo = tipoDiMezzo;
    }

    public long getCapienza() {
        return capienza;
    }

    public void setCapienza(long capienza) {
        this.capienza = capienza;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public UUID getMezzoDiTrasportoId() {
        return mezzoDiTrasportoId;
    }

    public void setMezzoDiTrasportoId(UUID mezzoDiTrasportoId) {
        this.mezzoDiTrasportoId = mezzoDiTrasportoId;
    }

    public Stato getStato() {
        return stato;
    }

    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

//    public Manutenzione getManutenzione(UUID id) {
//        List<Manutenzione> manutenzioneList = manutenzioni.stream().filter(manutenzione -> manutenzione.getMezzoDiTrasporto().getMezzoDiTrasportoId().equals(id)).toList();
//        return manutenzioneList.get(0);
//    }

    public LocalDate getDataDiImmatricolazione() {
        return dataDiImmatricolazione;
    }

    public void setDataDiImmatricolazione(LocalDate dataDiImmatricolazione) {
        this.dataDiImmatricolazione = dataDiImmatricolazione;
    }

    public List<Biglietto> getBigliettoList() {
        return bigliettoList;
    }

    @Override
    public String toString() {
        return "{ mezzoDiTrasportoId=" + mezzoDiTrasportoId +
                ", tipoDiMezzo=" + tipoDiMezzo +
                ", capienza=" + capienza +
                ", stato=" + stato +
                ", tratta=" + tratta +
                '}';
    }
}
