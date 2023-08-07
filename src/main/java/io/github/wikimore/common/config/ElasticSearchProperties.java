package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class ElasticSearchProperties {
  private int port = 9200;
  private String host = "127.0.0.1";
  private int connectTimeout = 3000;
  private int maxConnection = 100;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(int connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public int getMaxConnection() {
    return maxConnection;
  }

  public void setMaxConnection(int maxConnection) {
    this.maxConnection = maxConnection;
  }
}
