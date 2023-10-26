package DAO;

import entities.Biglietto;
import entities.DocumentoVendita;
import entities.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DocumentoVenditaDAO {

    private final EntityManager em;

    public DocumentoVenditaDAO(EntityManager em) {

        this.em = em;
    }

    public DocumentoVendita getById(UUID id) {
        return em.find(DocumentoVendita.class, id);
    }

    public void getAllTessere() {
        TypedQuery<Tessera> getAllTessereQuery = em.createQuery("SELECT t FROM Tessera t", Tessera.class);
        getAllTessereQuery.getResultList().forEach(tessera -> System.out.println("Tessera id: " + tessera.getDocumentoVenditaId() + "; " +
                " Nome:" + tessera.getUtente().getNome() + " Congome: " + tessera.getUtente().getCognome() + "; " +
                " Data di scadenza tessera: " + tessera.getDataDiScadenza() + " Abbonamneto: " + tessera.getTipoAbbonamento()));
    }

    public void controlloAbbonamento(String id) {
        UUID idTessera = UUID.fromString(id);
        try {
            TypedQuery<DocumentoVendita> query = em.createQuery(
                    "SELECT ts FROM Tessera ts WHERE ts.documentoVenditaId = :idTessera", DocumentoVendita.class);
            query.setParameter("idTessera", idTessera);
            Object result = query.getSingleResult();
            System.out.println(result);
            if (result != null) {
                SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
                TypedQuery<LocalDate> query2 = em.createQuery(
                        "SELECT ts.dataDiScadenza FROM Tessera ts WHERE ts.documentoVenditaId = :idTessera", LocalDate.class
                );
                query2.setParameter("idTessera", idTessera);
                LocalDate result2 = (LocalDate) query2.getSingleResult();

                if (result2.isBefore(LocalDate.now())) {
                    System.out.println("La tessera N:" + " " + id + " " + "è scaduta il: " + result2);
                } else {
                    System.out.println("Tessera ancora in corso di validità, scade il: " + result2);
                }
            }
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dati: " + e.getMessage());
        }
    }

    public void convalidaBiglietto(UUID id) {
        try {
            Query query = em.createQuery(
                    "UPDATE biglietto SET biglietto.dataDiConvalidazione = CURRENT_DATE " +
                            "WHERE biglietto.documentoVenditaId = :id");
            query.setParameter(":id", id);
            int updateCount = query.executeUpdate();
            System.out.println("Convalidazine avvenuta con successo");
        } catch (Exception e) {
            System.out.println("Oops c'è statoo un'errore nella convalidazione...");
            System.out.println(e.getMessage());
        }
    }

    public List<DocumentoVendita> dammiDocumentiVenditaPerDataePv(String idPuntoVendita) {
        UUID idPuntoVenditaS = UUID.fromString(idPuntoVendita);
        TypedQuery<DocumentoVendita> query = em.createQuery("SELECT dv FROM DocumentoVendita dv " +
                        "WHERE dv.puntoVendita.puntoVenditaId = :puntoVendita",
                DocumentoVendita.class);
        query.setParameter("puntoVendita", idPuntoVenditaS);
        return query.getResultList();
    }

    public void totaleBigliettiVidimati(String idMezzo) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        TypedQuery<Biglietto> query = em.createQuery("SELECT bl FROM Biglietto bl " +
                        "WHERE bl.mezzoDiTrasporto.mezzoDiTrasportoId = :idMezzo AND bl.dataDiConvalidazione IS NOT NULL",
                Biglietto.class);
        query.setParameter("idMezzo", uuidMezzo);
        List<Biglietto> result = query.getResultList();
        if (result.isEmpty()) {
            System.out.println("Non ci sono biglietti vidimati innerenti al mezzo di trasporto inserito");
        } else {
            System.out.println("Biglietti vidimati in totale: " + result.size());
        }

    }

    public void bigliettiVidimatiPerData(String idMezzo, LocalDate data) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        TypedQuery<Biglietto> query = em.createQuery("SELECT bl FROM Biglietto bl " +
                        "WHERE bl.mezzoDiTrasporto.mezzoDiTrasportoId = :idMezzo and bl.dataDiConvalidazione = :data",
                Biglietto.class);
        query.setParameter("idMezzo", uuidMezzo).setParameter("data", data);
        List<Biglietto> result = query.getResultList();
        if (result.isEmpty()) {
            System.out.println("Non ci sono biglietti vidimati innerenti al mezzo di trasporto inserito");
        } else {
            System.out.println("Biglietti vidimati in totale: " + result.size() + " il " + data);
        }
    }

    public void bigliettiVidimatiPerPeriodo(String idMezzo, LocalDate startDate, LocalDate endDate) {
        UUID uuidMezzo = UUID.fromString(idMezzo);
        TypedQuery<Biglietto> query = em.createQuery("SELECT bl FROM Biglietto bl " +
                        "WHERE bl.mezzoDiTrasporto.mezzoDiTrasportoId = :idMezzo and bl.dataDiConvalidazione BETWEEN :startDate AND :endDate",
                Biglietto.class);
        query.setParameter("idMezzo", uuidMezzo).setParameter("startDate", startDate).setParameter("endDate", endDate);
        List<Biglietto> result = query.getResultList();
        if (result.isEmpty()) {
            System.out.println("Non ci sono biglietti vidimati inerenti al mezzo di trasporto inserito");
        } else {
            System.out.println("Biglietti vidimati in totale tra il " + startDate + " e il " + endDate + " sono : " + result.size() + " biglietti ");
        }
    }

    public void save(DocumentoVendita dv) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(dv);
            transaction.commit();
            System.out.println("DOCUMENTO REGISTRATO!!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Errore nel caricamento dati!!");
            throw e;
        }
    }

    public void deleteDocumentoVenditaById(UUID id) {
        DocumentoVendita DocumentoVenditaToremove = getById(id);
        if (id != null) {
            EntityTransaction trc = em.getTransaction();
            try {
                if (DocumentoVenditaToremove != null) {
                    trc.begin();

                    em.remove(DocumentoVenditaToremove);
                    trc.commit();
                    System.out.println("Il Documento" + " " + id + " " + "è stato rimosso!!");
                } else {
                    System.out.println("Non è stato possibile trovare il Documento con N:" + " " + id + "!!");
                }
            } catch (Exception e) {
                if (trc.isActive()) {
                    trc.rollback();
                }
                System.out.println("Errore nel caricamento dati!!");
            }
        }

    }

    public void DocumentoVenditaRefresh(DocumentoVendita DocumentoVendita) {
        em.refresh(DocumentoVendita);
    }

}
