/*
 * 文件名称: ICollectService.java Copyright 2011-2014 Ximalaya All right reserved.
 */
package io.github.wikimore.common.filter;

import com.codahale.metrics.Timer;
import io.github.wikimore.common.metric.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 监控配置的接口调用时间Filter
 *
 * @author Ted Wang
 */
public final class MetricFilter extends GenericFilterBean {
  private static final Logger LOG = LoggerFactory.getLogger(MetricFilter.class);
  private static final String NO_MATCH_PATCH = "no_match_path";
  private final String[] statusMapping = new String[]{"0xx", "1xx", "2xx", "3xx", "4xx", "5xx"};

  @Override
  protected void initFilterBean() throws ServletException {
    LOG.info("Initializing MetricFilter {}", this);
    Metrics.getSingleton().enableSlf4j();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    long start = System.nanoTime();
    HttpStatusTraceResponse responseWrapper = new HttpStatusTraceResponse((HttpServletResponse) response);
    try {
      chain.doFilter(request, responseWrapper);
    } finally {
      long useTime = System.nanoTime() - start;
      String matchPath = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
      if (matchPath != null) {
        Timer timer = Metrics.getSingleton().getTimer(matchPath);
        timer.update(useTime, TimeUnit.NANOSECONDS);
      } else {
        Timer timer = Metrics.getSingleton().getTimer(NO_MATCH_PATCH);
        timer.update(useTime, TimeUnit.NANOSECONDS);
      }
      int idx = responseWrapper.getStatusCode() / 100;
      Metrics.getSingleton().incr(statusMapping[idx]);
    }
  }

  @Override
  public void destroy() {
    LOG.info("Destroy MetricFilter {}", this);
  }

  class HttpStatusTraceResponse extends HttpServletResponseWrapper {
    private int statusCode = 200;

    public HttpStatusTraceResponse(HttpServletResponse response) {
      super(response);
    }

    public int getStatusCode() {
      return statusCode;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
      super.sendError(sc, msg);
      this.statusCode = sc;
    }

    @Override
    public void sendError(int sc) throws IOException {
      super.sendError(sc);
      this.statusCode = sc;
    }

    @Override
    public void sendRedirect(String location) throws IOException {
      super.sendRedirect(location);
      this.statusCode = 301;
    }

    @Override
    public void setStatus(int sc) {
      super.setStatus(sc);
      this.statusCode = sc;
    }

    @Override
    public void setStatus(int sc, String sm) {
      super.setStatus(sc, sm);
      this.statusCode = sc;
    }

  }
}
