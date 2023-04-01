/**
 * This interface provides the ability to classes implemented these methods to 
 * manipulate the components according to the repository pattern, 
 * so the users cab add, update, delete and read the data like a basic control.
 * 
 * @author Ying Ting Liu
 */
package repository;

public interface Editable<T> {
    void add(T obj);
    T read(int id);
    void update(int id, T obj);
    void delete(int id);
}
