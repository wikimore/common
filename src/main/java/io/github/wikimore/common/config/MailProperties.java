package io.github.wikimore.common.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ted Wang
 */
public class MailProperties {
  private static final Charset DEFAULT_CHARSET;
  private String host;
  private Integer port;
  private String username;
  private String password;
  private String protocol = "smtp";
  private Charset defaultEncoding;
  private final Map<String, String> properties;

  public MailProperties() {
    this.defaultEncoding = DEFAULT_CHARSET;
    this.properties = new HashMap();
  }

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return this.port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getProtocol() {
    return this.protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public Charset getDefaultEncoding() {
    return this.defaultEncoding;
  }

  public void setDefaultEncoding(Charset defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }

  public Map<String, String> getProperties() {
    return this.properties;
  }

  static {
    DEFAULT_CHARSET = StandardCharsets.UTF_8;
  }
}
