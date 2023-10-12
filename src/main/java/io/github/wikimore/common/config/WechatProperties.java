package io.github.wikimore.common.config;

/**
 * @author Ted Wang
 */
public class WechatProperties {
  private String appid;
  private String secret;
  private String codeTokenUrl;
  private String accessTokenUrl;
  private String refreshTokenUrl;
  private String userInfoUrl;
  private String authUrl;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getCodeTokenUrl() {
    return codeTokenUrl;
  }

  public void setCodeTokenUrl(String codeTokenUrl) {
    this.codeTokenUrl = codeTokenUrl;
  }

  public String getAccessTokenUrl() {
    return accessTokenUrl;
  }

  public void setAccessTokenUrl(String accessTokenUrl) {
    this.accessTokenUrl = accessTokenUrl;
  }

  public String getRefreshTokenUrl() {
    return refreshTokenUrl;
  }

  public void setRefreshTokenUrl(String refreshTokenUrl) {
    this.refreshTokenUrl = refreshTokenUrl;
  }

  public String getAuthUrl() {
    return authUrl;
  }

  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }

  public String getUserInfoUrl() {
    return userInfoUrl;
  }

  public void setUserInfoUrl(String userInfoUrl) {
    this.userInfoUrl = userInfoUrl;
  }
}
