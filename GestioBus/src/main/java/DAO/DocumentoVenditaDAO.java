package DAO;


import entities.Biglietto;
import entities.DocumentoVendita;
import entities.PuntoVendita;
import entities.Tessera;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    public void controlloAbbonamento(UUID id) {
        try {
            TypedQuery<DocumentoVendita> query = em.createQuery("SELECT ts FROM Tessera ts WHERE ts.documentoVenditaId = :idTessera", DocumentoVendita.class);
            Query query2 = em.createQuery("SELECT ts.dataDiScadenza FROM Tessera ts WHERE ts.documentoVenditaId = :idTessera", DocumentoVendita.class);


            query.setParameter("idTessera", id);
            query2.setParameter("idTessera", id);
            Object result = query.getSingleResult();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

            if (result == null) {
                System.out.println("Non è stato possibile trovare il Documento con N:" + " " + id + "!!");
            } else {
                String result2 = (String) query2.getSingleResult();

                Date todayDate = sdformat.parse(sdformat.format(new Date()));
                Date d1 = sdformat.parse(result2);
                if (d1.compareTo(todayDate) > 0) {
                    System.out.println("La tessera N:" + " " + id + " " + "è scaduta il:" + " " + d1);
                } else {
                    System.out.println("Tessera ancora in corso di valisità, scade il:" + " " + d1);
                }

            }
        } catch (Exception e) {
            System.out.println("Errore nel caricamento dati: " + e.getMessage());
        }


    }

    public void convalidaBiglietto(UUID id){
        try {
            Query query = em.createQuery(
                    "UPDATE biglietto SET biglietto.dataDiConvalidazione = CURRENT_DATE " +
                            "WHERE biglietto.documentoVenditaId = :id");
            query.setParameter(":id", id);
            int updateCount = query.executeUpdate();
            System.out.println("Convalidazine avvenuta con successo");
        }catch (Exception e){
            System.out.println("Oops c'è statoo un'errore nella convalidazione...");
            System.out.println(e.getMessage());
        }
    }

    public List<Tessera> dammiTesserePerData(LocalDate data){
        TypedQuery<Tessera> query = em.createQuery("SELECT ts FROM Tessera ts " +
                "WHERE ts.dataDiRilascio = :data", Tessera.class);
        query.setParameter("data", data);
        return query.getResultList();

    }
    public List<Biglietto> dammiBigliettiPerData(PuntoVendita puntoV, LocalDate data){
        TypedQuery<Biglietto> query = em.createQuery("SELECT bl FROM Tessera bl " +
                "WHERE bl.dataDiRilascio = :data", Biglietto.class);
        query.setParameter("data", data);
        return query.getResultList();

    }public List<Tessera> dammiTesserePuntoEmissione(PuntoVendita puntoV, LocalDate data){
        TypedQuery<Tessera> query = em.createQuery("SELECT ts FROM Tessera ts " +
                "WHERE ts.dataDiRilascio = :data", Tessera.class);
        query.setParameter("data", data);
        return query.getResultList();

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
