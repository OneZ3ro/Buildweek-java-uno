package DAO;

import entities.ControlloTratta;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.UUID;

public class ControlloTrattaDAO {
    private final EntityManager em;

    public ControlloTrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ControlloTratta controlloTratta) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(controlloTratta);
        transaction.commit();
        System.out.println("Nuova controlloTratta creata correttamente");
    }

    public ControlloTratta getById(String cId) {
        UUID id = UUID.fromString(cId);
        return em.find(ControlloTratta.class, id);
    }

    public void getTempiEffettiviMezzo(String idMezzo) {
        UUID idConvertito = UUID.fromString(idMezzo);
        TypedQuery<ControlloTratta> query = em.createNamedQuery("getTempiEffettivi", ControlloTratta.class);
        query.setParameter("idMezzo", idConvertito);
        query.getResultList().forEach(check -> {
            System.out.println("Partenza: " + check.getMezzoDiTrasporto().getTratta().getPartenza() +
                    " Arrivo: " + check.getMezzoDiTrasporto().getTratta().getDestinazione() +
                    " tempo effettivo: " + check.getTempoEffettivo() + " tempo medio: " +
                    check.getMezzoDiTrasporto().getTratta().getTempoMedio());
        });

    }

    public void delete(UUID id) {
        ControlloTratta found = em.find(ControlloTratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("La controlloTratta è stata cancellata con successo");
        } else {
            System.err.println("La controlloTratta con id" + id + " non è stata trovata");
        }
    }

    public void refresh(UUID id) {
        ControlloTratta found = em.find(ControlloTratta.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("La controlloTratta è stata refreshata con successo");
        } else {
            System.err.println("La controlloTratta con id" + id + " non è stata trovata");
        }
    }
}
