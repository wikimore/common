package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class AliPayProperties {
  private String protocol;
  private String gatewayHost;
  private String signType;
  private String appId;
  private String merchantPrivateKey;
  private String alipayPublicKey;
  private String merchantCertPath;
  private String alipayCertPath;
  private String alipayRootCertPath;
  private String notifyUrl;

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getGatewayHost() {
    return gatewayHost;
  }

  public void setGatewayHost(String gatewayHost) {
    this.gatewayHost = gatewayHost;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getMerchantPrivateKey() {
    return merchantPrivateKey;
  }

  public void setMerchantPrivateKey(String merchantPrivateKey) {
    this.merchantPrivateKey = merchantPrivateKey;
  }

  public String getAlipayPublicKey() {
    return alipayPublicKey;
  }

  public void setAlipayPublicKey(String alipayPublicKey) {
    this.alipayPublicKey = alipayPublicKey;
  }

  public String getMerchantCertPath() {
    return merchantCertPath;
  }

  public void setMerchantCertPath(String merchantCertPath) {
    this.merchantCertPath = merchantCertPath;
  }

  public String getAlipayCertPath() {
    return alipayCertPath;
  }

  public void setAlipayCertPath(String alipayCertPath) {
    this.alipayCertPath = alipayCertPath;
  }

  public String getAlipayRootCertPath() {
    return alipayRootCertPath;
  }

  public void setAlipayRootCertPath(String alipayRootCertPath) {
    this.alipayRootCertPath = alipayRootCertPath;
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
}
