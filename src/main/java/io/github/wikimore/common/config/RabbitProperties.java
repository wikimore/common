package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class RabbitProperties {
  private String host = "localhost";
  private Integer port = 5672;
  private String username = "guest";
  private String password = "guest";
  private int channelCacheSize = 32;
  private boolean publisherReturns = false;
  private boolean publisherConfirms = false;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getChannelCacheSize() {
    return channelCacheSize;
  }

  public void setChannelCacheSize(int channelCacheSize) {
    this.channelCacheSize = channelCacheSize;
  }

  public boolean isPublisherReturns() {
    return publisherReturns;
  }

  public void setPublisherReturns(boolean publisherReturns) {
    this.publisherReturns = publisherReturns;
  }

  public boolean isPublisherConfirms() {
    return publisherConfirms;
  }

  public void setPublisherConfirms(boolean publisherConfirms) {
    this.publisherConfirms = publisherConfirms;
  }
}
