package com.quan.framework.spring.mvc.log;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Force-oneself
 * @date 2023-03-01
 */
@FunctionalInterface
public interface FilterRunnable {

    void run() throws IOException, ServletException;
}
