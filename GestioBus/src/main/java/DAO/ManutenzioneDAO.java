package DAO;

import entities.Manutenzione;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ManutenzioneDAO {
    private final EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void periodiServiziMezzo(String idMezzo) {
        UUID idConvertito = UUID.fromString(idMezzo);
        TypedQuery<LocalDate> query = em.createQuery("SELECT DISTINCT bl.dataDiConvalidazione FROM Biglietto bl " +
                "WHERE mezzoDiTrasporto.mezzoDiTrasportoId = :idMezzo and bl.dataDiConvalidazione IS NOT NULL", LocalDate.class);
        query.setParameter("idMezzo", idConvertito);

        query.getResultList().forEach(System.out::println);
    }


    public List<Manutenzione> listaManutenzioneMezzi(String mezzoId) {
        UUID idConvertito = UUID.fromString(mezzoId);

        TypedQuery<Manutenzione> query = em.createQuery("SELECT mm FROM Manutenzione mm WHERE mm.mezzoDiTrasporto.mezzoDiTrasportoId = :mezzoId ", Manutenzione.class);
        query.setParameter("mezzoId", idConvertito);


        return query.getResultList();
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

    public LocalDate getFineData(UUID id) {
        TypedQuery<LocalDate> localDateTypedQuery = em.createQuery("SELECT DISTINCT m.dataFine FROM Manutenzione m WHERE m.mezzoDiTrasporto.mezzoDiTrasportoId = :id", LocalDate.class);
        localDateTypedQuery.setParameter("id", id);
        return localDateTypedQuery.getSingleResult();
    }

}
