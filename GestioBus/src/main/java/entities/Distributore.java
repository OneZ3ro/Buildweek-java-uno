package entities;

import enums.StatoDistributore;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "distributori")
public class Distributore extends PuntoVendita {
    @Enumerated(EnumType.STRING)
    private StatoDistributore stato;

    public Distributore() {
    }

    public Distributore(String nome, String citta, StatoDistributore stato) {
        super(nome, citta);
        this.stato = stato;
    }
}
