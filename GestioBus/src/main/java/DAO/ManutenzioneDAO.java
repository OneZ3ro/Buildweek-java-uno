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

    }

    public List<Manutenzione> listaManutenzioneMezzi(String mezzoId) {
        UUID idConvertito = UUID.fromString(mezzoId);
        TypedQuery<Manutenzione> query = em.createQuery(
                "SELECT mm FROM Manutenzione mm WHERE mm.mezzoDiTrasporto.mezzoDiTrasportoId = :mezzoId ORDER BY mm.dataInizio ASC ",
                Manutenzione.class);
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

    public List<Manutenzione> getAllManutenzioni() {
        TypedQuery<Manutenzione> getAllManutenzioniQuery = em.createQuery("SELECT m FROM Manutenzione m",
                Manutenzione.class);
        return getAllManutenzioniQuery.getResultList();
    }

    public List<Manutenzione> getAllManutenzioniPerMezzo(String mId) {
        UUID id = UUID.fromString(mId);
        TypedQuery<Manutenzione> getAllManutenzioniPerMezzoQuery = em.createQuery(
                "SELECT m FROM Manutenzione m WHERE m.mezzoDiTrasporto.mezzoDiTrasportoId=:id", Manutenzione.class);
        getAllManutenzioniPerMezzoQuery.setParameter("id", id);
        return getAllManutenzioniPerMezzoQuery.getResultList();
    }

    public void uscitaMezzoManutenzione(String mId) {
        UUID id = UUID.fromString(mId);
        Manutenzione m = em.find(Manutenzione.class, id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        m.setDataFine();
        em.merge(m);
        transaction.commit();
        System.out.println("Data aggiornata");
    }

    public List<LocalDate> getFineData(UUID id) {
        TypedQuery<LocalDate> localDateTypedQuery = em.createQuery(
                "SELECT DISTINCT m.dataFine FROM Manutenzione m WHERE m.mezzoDiTrasporto.mezzoDiTrasportoId = :id",
                LocalDate.class);
        localDateTypedQuery.setParameter("id", id);
        return localDateTypedQuery.getResultList();
    }

}
