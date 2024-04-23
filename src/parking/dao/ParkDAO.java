
package parking.dao;

import java.util.List;

public interface ParkDAO<E, K> {
    abstract public void insert(E e);
    abstract public void update(E e);
    abstract public void delete(K id);
//    abstract public void restore(K id);
    abstract public List<E> selectALL();
    abstract public E selectByID(K id);
    abstract public List<E> selectBySQL(String sql, Object...args);
}
