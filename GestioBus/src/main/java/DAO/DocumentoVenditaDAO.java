package DAO;


import entities.DocumentoVendita;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.UUID;

public class DocumentoVenditaDAO {
    private final EntityManager em;

    public DocumentoVenditaDAO(EntityManager em) {

        this.em = em;
    }

    public DocumentoVendita getById(UUID id) {
        return em.find(DocumentoVendita.class, id);
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
