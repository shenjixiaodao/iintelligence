package com.ii.domain.base;


public interface State<T> {

  boolean sameStateAs(T other);

  /**
   * 依据间隔时间毫秒数，判断是否需要重新确认设备状态
   * @param other
   * @param seconds
   * @return
   */
  boolean reconfirmeState(T other, int seconds);

}
