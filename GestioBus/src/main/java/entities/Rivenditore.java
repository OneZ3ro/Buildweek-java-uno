package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rivenditori")
public class Rivenditore extends PuntoVendita {
    public Rivenditore() {
    }

    public Rivenditore(String nome, String citta) {
        super(nome, citta);
    }
}
