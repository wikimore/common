package io.github.wikimore.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Set response header cache attributes
 */
public class DisableCacheFilter extends GenericFilterBean {
  private static final Logger LOG = LoggerFactory.getLogger(DisableCacheFilter.class);

  @Override
  protected void initFilterBean() throws ServletException {
    LOG.info("Initializing DisableCacheFilter {}", this);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.addHeader("Cache-Control", "no-cache");
    httpResponse.addHeader("Cache-Control", "no-store");
    httpResponse.addHeader("Pragma", "no-cache");
    httpResponse.addDateHeader("Date", 0);
    httpResponse.addDateHeader("Expires", 0);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    LOG.info("Destroy DisableCacheFilter {}", this);
  }
}
