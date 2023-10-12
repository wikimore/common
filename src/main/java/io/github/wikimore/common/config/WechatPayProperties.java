package io.github.wikimore.common.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Ted Wang
 */
public class WechatPayProperties {
  /**
   * 商户号
   */
  private String appid;

  private String merchantId;
  /**
   * 商户API私钥路径
   */
  private String privateKeyPath;
  /**
   * 商户证书序列号
   */
  private String merchantSerialNumber;
  /**
   * 商户APIV3密钥
   */
  private String apiV3key;
  /**
   * 商户APIV3密钥
   */
  private String wechatPayCertificatePath;

  private String alipayOrderTimeExpire;
  /**
   * 微信订单支付结果回调地址
   */
  private String wechatPayNotifyUrl;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getPrivateKeyPath() {
    return privateKeyPath;
  }

  public void setPrivateKeyPath(String privateKeyPath) {
    this.privateKeyPath = privateKeyPath;
  }

  public String getMerchantSerialNumber() {
    return merchantSerialNumber;
  }

  public void setMerchantSerialNumber(String merchantSerialNumber) {
    this.merchantSerialNumber = merchantSerialNumber;
  }

  public String getApiV3key() {
    return apiV3key;
  }

  public void setApiV3key(String apiV3key) {
    this.apiV3key = apiV3key;
  }

  public String getWechatPayCertificatePath() {
    return wechatPayCertificatePath;
  }

  public void setWechatPayCertificatePath(String wechatPayCertificatePath) {
    this.wechatPayCertificatePath = wechatPayCertificatePath;
  }

  public String getAlipayOrderTimeExpire() {
    return alipayOrderTimeExpire;
  }

  public void setAlipayOrderTimeExpire(String alipayOrderTimeExpire) {
    this.alipayOrderTimeExpire = alipayOrderTimeExpire;
  }

  public String getWechatPayNotifyUrl() {
    return wechatPayNotifyUrl;
  }

  public void setWechatPayNotifyUrl(String wechatPayNotifyUrl) {
    this.wechatPayNotifyUrl = wechatPayNotifyUrl;
  }
}
