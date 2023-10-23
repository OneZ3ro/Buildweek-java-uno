package entities;

import javax.persistence.Entity;

@Entity
public class Distributore extends PuntoVendita{
    public Distributore(){}

    public Distributore(String nome, String citta) {
        super(nome, citta);
    }
}
