package io.github.wikimore.common.customizer;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.ProtocolHandler;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.util.ObjectUtils;

/**
 * @author Ted Wang
 */
public class DefaultTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
  private int maxThreads = 10;
  private int minSpareThreads = 10;
  private int maxConnections = 100;

  public DefaultTomcatConnectorCustomizer(int maxThreads, int minSpareThreads, int maxConnections) {
    this.maxThreads = maxThreads;
    this.minSpareThreads = minSpareThreads;
    this.maxConnections = maxConnections;
  }

  @Override
  public void customize(Connector connector) {
    ProtocolHandler protocolHandler = connector.getProtocolHandler();
    if (ObjectUtils.isEmpty(protocolHandler)) {
      return;
    }
    if (protocolHandler instanceof AbstractProtocol) {
      AbstractProtocol protocol = (AbstractProtocol) protocolHandler;
      protocol.setMaxThreads(maxThreads);
      protocol.setMinSpareThreads(minSpareThreads);
      protocol.setMaxConnections(maxConnections);
    }
  }
}