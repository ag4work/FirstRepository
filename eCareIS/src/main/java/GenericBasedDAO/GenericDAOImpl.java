package GenericBasedDAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexey on 25.06.2015.
 */
public class GenericDAOImpl<T> implements  DAOOperations<T> {

    private EntityManagerFactory emf = null;

    private Class<T> genericClass;

    protected GenericDAOImpl(final Class<T> genericClass, EntityManagerFactory emf) {
        this.genericClass = genericClass;
        this.emf = emf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT a FROM " + genericClass.getName() + " a");
        List<T> entities = query.getResultList();

        em.close();
        return entities;
    }

    @Override
    public T get(final Serializable id) {
        EntityManager em = emf.createEntityManager();

        T entity = em.find(genericClass, id);
        if (entity == null) {
            throw new EntityNotFoundException("Not found " + genericClass.getName() + " for ID "
                    + id);
        }
        em.close();
        return entity;
    }

    @Override
    public void delete(final Serializable id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            T entity = em.find(genericClass, id);
            if (entity == null) {
                throw new EntityNotFoundException("Not found " + genericClass.getName() + " for ID "
                        + id);
            }
            em.remove(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            pex.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }

    }

    @Override
    public T update(final T entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        T storedEntity = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            storedEntity = em.merge(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            pex.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        } finally {
            em.close();
        }
        return storedEntity;
    }

    @Override
    public void add(T entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (PersistenceException pex) {
            pex.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        } finally {
            em.close();

        }
    }
}
