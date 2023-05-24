package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class TomcatServerConfig {
  private int port = 8080;
  private int maxThreads = 10;
  private int minThreads = 10;
  private int maxConnections = 100;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getMaxThreads() {
    return maxThreads;
  }

  public void setMaxThreads(int maxThreads) {
    this.maxThreads = maxThreads;
  }

  public int getMinThreads() {
    return minThreads;
  }

  public void setMinThreads(int minThreads) {
    this.minThreads = minThreads;
  }

  public int getMaxConnections() {
    return maxConnections;
  }

  public void setMaxConnections(int maxConnections) {
    this.maxConnections = maxConnections;
  }
}
