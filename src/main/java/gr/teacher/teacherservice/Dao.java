package gr.teacher.teacherservice;

import java.io.Serializable;
import java.util.List;

public interface Dao <T extends Serializable> {
    public void setClazz( Class<T> clazzToSet );

    T findOne( int id );

    List<T> findAll();

    void create(final T entity);

    void update(final T entity);

    void delete(final int Id);


}
