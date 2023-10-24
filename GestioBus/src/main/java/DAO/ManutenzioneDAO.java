package DAO;

import entities.Manutenzione;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager em;
    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione manutenzione) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(manutenzione);
        transaction.commit();
        System.out.println("Nuova manutenzione creata correttamente");
    }

    public Manutenzione getById(UUID id) {
        return em.find(Manutenzione.class, id);
    }

    public void delete(UUID id) {
        Manutenzione found = em.find(Manutenzione.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("La manutenzione è stata cancellata con successo");
        } else {
            System.err.println("La manutenzione con id" + id + " non è stata trovata");
        }
    }

    public void refresh(UUID id) {
        Manutenzione found = em.find(Manutenzione.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("La manutenzione è stata refreshata con successo");
        } else {
            System.err.println("La manutenzione con id" + id + " non è stata trovata");
        }
    }
}