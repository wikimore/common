package io.github.wikimore.common.web;

/**
 * @author Ted Wang
 */
public final class WebResult<T> {

  private int code;
  private String msg;
  private T data;

  public WebResult() {
    this.code = 0;
    this.msg = null;
    this.data = null;
  }

  public WebResult(T data) {
    this.code = 0;
    this.msg = null;
    this.data = data;
  }

  public WebResult(int code, T data) {
    this.code = code;
    this.msg = null;
    this.data = data;
  }

  public WebResult(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public WebResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setData(T data) {
    this.data = data;
  }

}
