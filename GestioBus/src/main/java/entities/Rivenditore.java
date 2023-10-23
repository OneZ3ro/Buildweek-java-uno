package entities;

import javax.persistence.Entity;

@Entity
public class Rivenditore extends PuntoVendita{
    public Rivenditore(){}
    public Rivenditore(String nome, String citta) {
        super(nome, citta);
    }
}
