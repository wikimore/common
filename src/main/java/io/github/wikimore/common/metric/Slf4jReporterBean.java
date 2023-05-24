/*
 * 文件名称: Slf4jReporterBean.java Copyright 2011-2014 Ximalaya All right reserved.
 */
package io.github.wikimore.common.metric;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 集成Slf4J日志和Spring，通过配置输出mtric日志
 * 
 * @author Ted Wang
 */
public class Slf4jReporterBean implements InitializingBean, DisposableBean {

  private int period = 1;

  public void setPeriod(int period) {
    this.period = period;
  }

  @Override
  public void destroy() throws Exception {
    Metrics.getSingleton().disableSlf4j();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    Metrics.getSingleton().enableSlf4j(period);
  }
}
