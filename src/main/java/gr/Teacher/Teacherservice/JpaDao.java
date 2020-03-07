package gr.Teacher.Teacherservice;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class JpaDao<T extends Serializable> implements Dao<T> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(Long id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
