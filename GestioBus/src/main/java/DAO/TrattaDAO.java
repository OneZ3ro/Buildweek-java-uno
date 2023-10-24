package DAO;

import entities.Tratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class TrattaDAO {
    private final EntityManager em;
    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();
        System.out.println("Nuova tratta creata correttamente");
    }

    public Tratta getById(UUID id) {
        return em.find(Tratta.class, id);
    }

    public void delete(UUID id) {
        Tratta found = em.find(Tratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("La tratta è stata cancellata con successo");
        } else {
            System.err.println("La tratta con id" + id + " non è stata trovata");
        }
    }

    public void refresh(UUID id) {
        Tratta found = em.find(Tratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("La tratta è stata refreshata con successo");
        } else {
            System.err.println("La tratta con id" + id + " non è stata trovata");
        }
    }
}
