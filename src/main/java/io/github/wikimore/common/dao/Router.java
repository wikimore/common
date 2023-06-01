package io.github.wikimore.common.dao;


/**
 * @author Ted Wang
 */
public interface Router {
  /**
   * 路由，获取目标分表
   *
   * @param key
   * @return
   */
  Shard route(Object key);

}
