package DAO;


import entities.DocumentoVendita;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.UUID;

public class DocumentoVenditaDAO {

    private final EntityManager em;

    public DocumentoVenditaDAO(EntityManager em) {

        this.em = em;
    }

    public DocumentoVendita getById(UUID id) {
        return em.find(DocumentoVendita.class, id);
    }

    public void controlloAbbonamento() {
       /* try {
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
        }*/
        LocalDate oggi = LocalDate.now();
        TypedQuery<DocumentoVendita> query = em.createQuery("SELECT ts FROM Tessera ts WHERE ts.dataDiScadenza > :oggi", DocumentoVendita.class);
        query.setParameter("oggi", oggi);
        query.getResultList().forEach(System.out::println);
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
