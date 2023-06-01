/*
 * 文件名称: BusinessException.java Copyright 2011-2015 Ximalaya All right reserved.
 */
package io.github.wikimore.common.web;

/**
 * 业务异常父类
 * 
 * @author Ted Wang
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = -7610666750107941464L;

  private int code;

  public BusinessException(int code) {
    super();
    this.code = code;
  }

  public BusinessException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

}
