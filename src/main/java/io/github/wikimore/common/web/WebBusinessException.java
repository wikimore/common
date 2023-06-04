package io.github.wikimore.common.web;


/**
 * Web业务异常，可以从抛出，但是必须配置BusinessHandlerExceptionResolver
 *
 * @author Ted Wang
 */
public class WebBusinessException extends BusinessException {
  private int statusCode = 500; // http状态码

  public WebBusinessException(int code) {
    this(code, "unexpected error");
  }

  public WebBusinessException(int code, int errorHttpStatusCode) {
    this(code, "unexpected error", errorHttpStatusCode);
  }

  public WebBusinessException(int code, String message) {
    super(code, message);
  }

  public WebBusinessException(int code, String message, int errorHttpStatusCode) {
    this(code, message);
    this.statusCode = errorHttpStatusCode;
  }

  public int getStatusCode() {
    return this.statusCode;
  }

}
