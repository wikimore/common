/*
 * 文件名称: WebResult.java Copyright 2011-2015 Ximalaya All right reserved.
 */
package io.github.wikimore.common.web;

import java.lang.annotation.*;

/**
 * 注解表示一个方法返回的对象将被包装到一个WebResult的data字段中
 *
 * @author Ted Wang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Response {

}
