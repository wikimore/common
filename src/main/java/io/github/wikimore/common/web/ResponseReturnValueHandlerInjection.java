/*
 * 文件名称: HandlerAdapterPostProcessor.java Copyright 2011-2015 Ximalaya All right reserved.
 */
package io.github.wikimore.common.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责注入ResponseReturnValueHandler到所有ReturnValueHandler的列表第一位
 *
 * @author Ted Wang
 */
public class ResponseReturnValueHandlerInjection implements BeanPostProcessor {
  @Autowired
  private MessageSource messageSource;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof RequestMappingHandlerAdapter) {
      RequestMappingHandlerAdapter requestMappingHandlerAdapter = (RequestMappingHandlerAdapter) bean;
      List<HandlerMethodReturnValueHandler> defaultHandlerMethodReturnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
      StringHttpMessageConverter httpMessageConverter = null;
      for (HttpMessageConverter<?> messageConverter : requestMappingHandlerAdapter.getMessageConverters()) {
        if (messageConverter instanceof StringHttpMessageConverter) {
          httpMessageConverter = (StringHttpMessageConverter) messageConverter;
        }
      }
      httpMessageConverter.setWriteAcceptCharset(false);
      HandlerMethodReturnValueHandler customHandlerMethodReturnValueHandler = new ResponseReturnValueHandler(
              httpMessageConverter, messageSource);
      List<HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlers = new ArrayList<HandlerMethodReturnValueHandler>(
              defaultHandlerMethodReturnValueHandlers.size() + 1);
      handlerMethodReturnValueHandlers.add(customHandlerMethodReturnValueHandler);
      for (HandlerMethodReturnValueHandler handler : defaultHandlerMethodReturnValueHandlers) {
        handlerMethodReturnValueHandlers.add(handler);
      }
      requestMappingHandlerAdapter.setReturnValueHandlers(handlerMethodReturnValueHandlers);
    }
    return bean;
  }
}
