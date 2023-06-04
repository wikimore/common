package io.github.wikimore.common.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Ted Wang
 */
public abstract class AbstractPathFilter extends GenericFilterBean {
  private List<String> includePathPatterns;
  private List<String> excludePathPatterns;
  private List<Pattern> preparedIncludePathPatterns = new ArrayList<Pattern>();
  private List<Pattern> preparedExcludePathPatterns = new ArrayList<Pattern>();

  public List<String> getExcludePathPatterns() {
    return excludePathPatterns;
  }

  public void setExcludePathPatterns(List<String> excludePathPatterns) {
    this.excludePathPatterns = excludePathPatterns;
  }

  public List<String> getIncludePathPatterns() {
    return includePathPatterns;
  }

  public void setIncludePathPatterns(List<String> includePathPatterns) {
    this.includePathPatterns = includePathPatterns;
  }

  @Override
  protected void initFilterBean() throws ServletException {
    preparedIncludePathPatterns.clear();
    preparedExcludePathPatterns.clear();
    if (null != getIncludePathPatterns()) {
      for (String includePattern : getIncludePathPatterns()) {
        preparedIncludePathPatterns.add(Pattern.compile(includePattern, Pattern.CASE_INSENSITIVE));
      }
    }
    if (null != getExcludePathPatterns()) {
      for (String excludePattern : getExcludePathPatterns()) {
        preparedExcludePathPatterns.add(Pattern.compile(excludePattern, Pattern.CASE_INSENSITIVE));
      }
    }
    doInitFilterBean();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    String uri = httpServletRequest.getRequestURI();
    if (isExcludePath(uri)) {
      doFilterExclude(httpServletRequest, httpServletResponse, chain);
    } else if (isIncludePath(uri)) {
      doFilterInclude(httpServletRequest, httpServletResponse, chain);
    } else {
      doFilterIgnore(httpServletRequest, httpServletResponse, chain);
    }
  }

  @Override
  public void destroy() {
    preparedIncludePathPatterns.clear();
    preparedExcludePathPatterns.clear();
    doDestroy();
  }

  protected void doDestroy() {

  }

  protected void doInitFilterBean() throws ServletException {

  }

  protected abstract void doFilterInclude(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException;

  protected void doFilterExclude(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

  protected void doFilterIgnore(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
  }

  private boolean isExcludePath(String path) {
    for (Pattern excludePattern : preparedExcludePathPatterns) {
      if (excludePattern.matcher(path).find()) {
        return true;
      }
    }
    return false;
  }

  private boolean isIncludePath(String path) {
    for (Pattern includePattern : preparedIncludePathPatterns) {
      if (includePattern.matcher(path).find()) {
        return true;
      }
    }
    return false;
  }
}
