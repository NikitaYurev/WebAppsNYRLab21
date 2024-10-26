package csit.semit.nyr.webappsnyrlab21.entity;

import java.util.List;

public interface DAO<E, K> {
    List<E> getAllList();
    E findById(K id);
    boolean insert(E entity);
    boolean update(K id, E entity);
    boolean delete(E entity);
}