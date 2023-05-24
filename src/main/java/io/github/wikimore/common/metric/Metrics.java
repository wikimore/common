/*
 * 文件名称: Metrics.java Copyright 2011-2014 Ximalaya All right reserved.
 */
package io.github.wikimore.common.metric;

import com.codahale.metrics.*;
import com.codahale.metrics.Slf4jReporter.LoggingLevel;
import com.codahale.metrics.Timer.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Metric Wrapper for convenience use
 * 
 * @author Ted Wang
 */
public class Metrics {
  private static final Logger LOG = LoggerFactory.getLogger(Metrics.class);
  private Slf4jReporter slf4jReporter;
  private static final Metrics SINGLETON = new Metrics();

  public static final Metrics getSingleton() {
    return SINGLETON;
  }

  private final MetricRegistry metricRegistry = new MetricRegistry();

  private Metrics() {
  }

  public MetricRegistry getMetricRegistry() {
    return metricRegistry;
  }

  public void incr(String name) {
    metricRegistry.counter(name).inc();
  }

  public void incr(String name, long n) {
    metricRegistry.counter(name).inc(n);
  }

  public void decr(String name) {
    metricRegistry.counter(name).dec();
  }

  public void decr(String name, long n) {
    metricRegistry.counter(name).dec(n);
  }

  public void mark(String name) {
    metricRegistry.meter(name).mark();
  }

  public void mark(String name, long n) {
    metricRegistry.meter(name).mark(n);
  }

  public void update(String name, int n) {
    metricRegistry.histogram(name).update(n);
  }

  public void update(String name, long n) {
    metricRegistry.histogram(name).update(n);
  }

  public Context time(String name) {
    return metricRegistry.timer(name).time();
  }

  public Counter getCounter(String name) {
    return metricRegistry.counter(name);
  }

  public Meter getMeter(String name) {
    return metricRegistry.meter(name);
  }

  public Histogram getHistogram(String name) {
    return metricRegistry.histogram(name);
  }

  public Timer getTimer(String name) {
    return metricRegistry.timer(name);
  }

  /**
   * Enable console reporting for this MetricRegistry with frequency 1 minute.
   */
  public synchronized void enableSlf4j() {
    enableSlf4j(1L);
  }

  /**
   * Enable console reporting for this MetricRegistry.
   * 
   * @param minutes how often to output data to the console, in minutes.
   */
  public synchronized void enableSlf4j(long minutes) {
    if (slf4jReporter == null) {
      LOG.info("Starting console reporting with frequency {} minutes", minutes);
      slf4jReporter = Slf4jReporter.forRegistry(metricRegistry).outputTo(LoggerFactory.getLogger(Metrics.class))
          .convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).filter(MetricFilter.ALL)
          .withLoggingLevel(LoggingLevel.INFO).build();
      slf4jReporter.start(minutes, TimeUnit.MINUTES);
    }

  }

  /**
   * Disable console reporting for this MetricRegistry.
   */
  public synchronized void disableSlf4j() {
    if (slf4jReporter != null) {
      slf4jReporter.stop();
      slf4jReporter = null;
    }
  }
}
