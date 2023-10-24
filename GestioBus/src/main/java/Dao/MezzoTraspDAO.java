package Dao;

import entities.MezzoDiTrasporto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MezzoTraspDAO {

  private final EntityManager em;

  public MezzoTraspDAO(EntityManager em) {
    this.em = em;
  }


  public  void getTransportStatusById(UUID idMezzo) {
    try {
      TypedQuery<MezzoDiTrasporto> getResultQuery = em.createQuery("SELECT mt.stato FROM MezzoDiTrasporto mt WHERE mt.mezzoDiTrasportoId = :idMezzo", MezzoDiTrasporto.class);
      getResultQuery.setParameter("idMezzo", idMezzo);
      Object result = getResultQuery.getSingleResult();
      if (result == null) {
        System.out.println("Ci dispiace l'id inserito è errato");
      } else {
        System.out.println(result);
      }

    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public void save(MezzoDiTrasporto MezzoDiTrasporto) {
      EntityTransaction trc = em.getTransaction();
      try {
        trc.begin();
        em.persist(MezzoDiTrasporto);
        trc.commit();
        System.out.println("New MezzoDiTrasporto was added successfully");
      } catch (Exception e) {
        if (trc.isActive()) {
          trc.rollback();
        }
        System.out.println("There was an error loading data: ");
        throw e;
      }
    }

    public MezzoDiTrasporto getById(String id) {
      return em.find(MezzoDiTrasporto.class, id);
    }

    public void deleteMezzoDiTrasportoById(String id) {
      MezzoDiTrasporto MezzoDiTrasportoToremove = getById(id);
      if (id != null) {
        EntityTransaction trc = em.getTransaction();
        try {
          if (MezzoDiTrasportoToremove != null) {
            trc.begin();

            em.remove(MezzoDiTrasportoToremove);
            trc.commit();
            System.out.println("The even was removed successfully");
          } else {
            System.out.println("There was not found element with this id.");
          }
        } catch (Exception e) {
          if (trc.isActive()) {
            trc.rollback();
          }
          System.out.println("There was an error loading data");
        }
      }

    }

    public void MezzoDiTrasportoRefresh(MezzoDiTrasporto MezzoDiTrasporto) {
      em.refresh(MezzoDiTrasporto);
    }
  }

