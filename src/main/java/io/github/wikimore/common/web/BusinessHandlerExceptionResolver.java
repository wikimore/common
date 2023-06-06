package io.github.wikimore.common.web;

import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


/**
 * 默认的异常处理类
 * <p>
 * WebBusinessException，并作出相应的处理，其他异常默认处理
 *
 * @author Ted Wang
 */
public class BusinessHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
  private static final Logger LOG = LoggerFactory.getLogger(BusinessHandlerExceptionResolver.class);
  @Autowired
  private MessageSource messageSource;
  private final Set<Integer> errorStatusCodes = new HashSet<Integer>();

  public BusinessHandlerExceptionResolver() {
    setPreventResponseCaching(true); // 防止错误结果被cache
    errorStatusCodes.add(400);
    errorStatusCodes.add(401);
    errorStatusCodes.add(403);
    errorStatusCodes.add(404);
    errorStatusCodes.add(500);
  }

  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    String message = "unknown error";
    int code = 0;
    if (ex instanceof WebBusinessException) {
      WebBusinessException businessException = (WebBusinessException) ex;
      // 组装message
      code = businessException.getCode();
      if (messageSource != null) {
        message = messageSource.getMessage(String.valueOf(businessException.getCode()), null,
                                           businessException.getMessage(), null);
      } else {
        message = businessException.getMessage();
      }
      String jsonMessage = buildJSONMessage(code, message);
      if (businessException.getStatusCode() != HttpStatus.OK.value()) {
        int errorStatusCode = businessException.getStatusCode();
        boolean isLegalStatusCode = isErrorStatusCode(errorStatusCode);
        if (!isLegalStatusCode) {
          errorStatusCode = 500;
        }
        handleErrorResponse(request, response, errorStatusCode, jsonMessage);
      } else {
        handle200Response(request, response, jsonMessage);
      }
    } else if (ex instanceof HttpRequestMethodNotSupportedException) {
      LOG.warn("HttpRequestMethodNotSupportedException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_METHOD_NOT_ALLOWED, jsonMessage);
    } else if (ex instanceof HttpMediaTypeNotSupportedException) {
      LOG.warn("HttpMediaTypeNotSupportedException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, jsonMessage);
    } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
      LOG.warn("HttpMediaTypeNotAcceptableException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_NOT_ACCEPTABLE, jsonMessage);
    } else if (ex instanceof MissingPathVariableException) {
      LOG.warn("MissingPathVariableException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof MissingServletRequestParameterException) {
      LOG.warn("MissingServletRequestParameterException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof ServletRequestBindingException) {
      LOG.warn("ServletRequestBindingException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof ConversionNotSupportedException) {
      LOG.warn("ConversionNotSupportedException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, jsonMessage);
    } else if (ex instanceof TypeMismatchException) {
      LOG.warn("TypeMismatchException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof HttpMessageNotReadableException) {
      LOG.warn("HttpMessageNotReadableException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof HttpMessageNotWritableException) {
      LOG.warn("HttpMessageNotWritableException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, jsonMessage);
    } else if (ex instanceof MethodArgumentNotValidException) {
      LOG.warn("MethodArgumentNotValidException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof MissingServletRequestPartException) {
      LOG.warn("MissingServletRequestPartException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof BindException) {
      LOG.warn("BindException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_BAD_REQUEST, jsonMessage);
    } else if (ex instanceof NoHandlerFoundException) {
      LOG.warn("NoHandlerFoundException {}, url is {}", ex.getMessage(), request.getRequestURI());
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_NOT_FOUND, jsonMessage);
    } else {
      String errorMessage = buildLogMessage(ex, request);
      LOG.warn(errorMessage, ex);
      String jsonMessage = buildJSONMessage(BizCode.SYSTEM_ERROR, ex.getMessage());
      handleErrorResponse(request, response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, jsonMessage);
    }
    return new ModelAndView();
  }

  protected String buildJSONMessage(int code, String message) {
    WebResult result = new WebResult(code, message);
    return JSON.toJSONString(result);
  }

  @Override
  protected String buildLogMessage(Exception ex, HttpServletRequest request) {
    StringBuilder sb = new StringBuilder();
    // request uri path
    sb.append("unexpected exception with request url ").append(request.getRequestURI()).append(" and method = ")
            .append(request.getMethod()).append("\r\n");
    // TODO:增加打印RequestBody的逻辑
    // request cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      sb.append("Cookies are [");
      for (int i = 0; i < cookies.length; i++) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(cookies[i].getName()).append("=").append(cookies[i].getValue());
      }
      sb.append("]\r\n");
    }
    return sb.toString();
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  private void handleErrorResponse(HttpServletRequest request, HttpServletResponse response, int statusCode, String message) {
    try {
      response.setStatus(statusCode);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      response.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      LOG.error("send response failed, request url is {}", request.getRequestURI(), e);
    } catch (IllegalStateException e) {
      LOG.warn("IllegalStateException, send response failed, status code had already set, request url is {}",
               request.getRequestURI());
    }
  }

  private void handle200Response(HttpServletRequest request, HttpServletResponse response, String message) {
    try {
      response.setStatus(HttpStatus.OK.value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      response.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      LOG.error("send 200 response failed, request url is {}", request.getRequestURI(), e);
    } catch (IllegalStateException e) {
      LOG.warn("IllegalStateException, send 200 response failed, request url is {}", request.getRequestURI());
    }
  }

  private boolean isErrorStatusCode(int statusCode) {
    return errorStatusCodes.contains(statusCode);
  }


}
