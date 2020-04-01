package gr.teacher.teacherservice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class JpaDao<T extends Serializable> implements Dao<T> {

    protected Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(int id) {
        T entity = entityManager.find(clazz, id);
        return entity;
    }

    public List<T> findAll() {
        List<T> result = entityManager.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        return result == null ? new ArrayList<>() : result;
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(int Id) {
        System.out.println("paei na to vrei");
        T entity = findOne(Id);
        System.out.println("to id einai "+Id);
        entityManager.remove(entity);
    }
}
