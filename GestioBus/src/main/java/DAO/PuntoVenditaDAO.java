package DAO;

import entities.PuntoVendita;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class PuntoVenditaDAO {
    private final EntityManager em;
    public PuntoVenditaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoVendita puntoVendita) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(puntoVendita);
        transaction.commit();
        System.out.println("Nuovo puntoVendita creato correttamente");
    }

    public PuntoVendita getById(UUID id) {
        return em.find(PuntoVendita.class, id);
    }

    public void delete(UUID id) {
        PuntoVendita found = em.find(PuntoVendita.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Il puntoVendita è stato cancellato con successo");
        } else {
            System.err.println("Il puntoVendita con id" + id + " non è stato trovato");
        }
    }

    public void refresh(UUID id) {
        PuntoVendita found = em.find(PuntoVendita.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("Il puntoVendita è stato refreshato con successo");
        } else {
            System.err.println("Il puntoVendita con id" + id + " non è stato trovato");
        }
    }
}
