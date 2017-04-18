package com.ii.domain.base;

/**
 * An criteria, as explained in the DDD book.
 *  
 */
public interface State<T> {

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other criteria.
   * @return true if the identities are the same, regardles of other attributes.
   */
  boolean sameStateAs(T other);

}
