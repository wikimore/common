package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class KafkaBrokerProperties {
  private String bootstrapServers;

  public String getBootstrapServers() {
    return bootstrapServers;
  }

  public void setBootstrapServers(String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }
}
