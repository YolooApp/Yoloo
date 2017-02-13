package com.yoloo.backend.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Helper to allow for optimizations for different types of Map/Collections
 *
 * @param <K> The key type of the map
 * @param <V> The value type of the map/collection
 * @author Scott Hernandez
 */
public final class IterHelper<K, V> {
  /**
   * Process a Map
   *
   * @param x the object to process
   * @param callback the value
   */
  @SuppressWarnings("unchecked")
  public void loopMap(final Object x, final MapIterCallback<K, V> callback) {
    if (x == null) {
      return;
    }

    if (x instanceof Collection) {
      throw new IllegalArgumentException("call loop instead");
    }

    if (x instanceof HashMap<?, ?>) {
      if (((HashMap) x).isEmpty()) {
        return;
      }

      final HashMap<?, ?> hm = (HashMap<?, ?>) x;
      for (final Entry<?, ?> e : hm.entrySet()) {
        callback.eval((K) e.getKey(), (V) e.getValue());
      }
      return;
    }
    if (x instanceof Map) {
      final Map<K, V> m = (Map<K, V>) x;
      for (final Entry<K, V> entry : m.entrySet()) {
        callback.eval(entry.getKey(), entry.getValue());
      }
      return;
    }
  }

  /**
   * Calls eval for each entry found, or just once if the "x" isn't iterable/collection/listFeed/etc.
   * with "x"
   *
   * @param x the object process
   * @param callback the value
   */
  @SuppressWarnings({"unchecked"})
  public void loopOrSingle(final Object x, final IterCallback<V> callback) {
    if (x == null) {
      return;
    }

    //A collection
    if (x instanceof Collection<?>) {
      final Collection<?> l = (Collection<?>) x;
      for (final Object o : l) {
        callback.eval((V) o);
      }
      return;
    }

    //An array of Object[]
    if (x.getClass().isArray()) {
      for (final Object o : (Object[]) x) {
        callback.eval((V) o);
      }
      return;
    }

    callback.eval((V) x);
  }

  /**
   * A value mechanism for processing Maps
   *
   * @param <K> the type map keys
   * @param <V> the type map values
   */
  public interface MapIterCallback<K, V> {
    /**
     * The method to call in the value
     *
     * @param k the key from the map
     * @param v the value for the key
     */
    void eval(K k, V v);
  }

  /**
   * A value mechanism for processing Iterables
   *
   * @param <V> the type Iterable elements
   */
  public interface IterCallback<V> {
    /**
     * The method to call in the value
     *
     * @param v an element in the Iterable
     */
    void eval(V v);
  }
}
