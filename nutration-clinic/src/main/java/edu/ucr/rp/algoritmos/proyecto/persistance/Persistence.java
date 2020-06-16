package edu.ucr.rp.algoritmos.proyecto.persistance;

/**
 *
 * @param <E> Type of element.
 * @param <T> Type of Collection.
 */
public interface Persistence<E, T> {
    boolean write(T t);
    T read();
    boolean deleteAll();
}
