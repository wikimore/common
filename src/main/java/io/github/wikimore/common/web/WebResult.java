package io.github.wikimore.common.web;

/**
 * @author Ted Wang
 */
public final class WebResult<T> {

  private int ret;
  private String msg;
  private T data;

  public WebResult() {
    this.ret = 0;
    this.msg = null;
    this.data = null;
  }

  public WebResult(T data) {
    this.ret = 0;
    this.msg = null;
    this.data = data;
  }

  public WebResult(int ret, T data) {
    this.ret = ret;
    this.msg = null;
    this.data = data;
  }

  public WebResult(int ret, String msg, T data) {
    this.ret = ret;
    this.msg = msg;
    this.data = data;
  }

  public int getRet() {
    return ret;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }

  public void setRet(int ret) {
    this.ret = ret;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setData(T data) {
    this.data = data;
  }

}
