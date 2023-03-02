package com.quan.framework.spring.mvc.xss;

import com.quan.framework.spring.mvc.wrapper.RepeatableHttpServletRequestWrapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Force-oneself
 * @date 2023-03-02
 */
public class XssHttpServletRequestWrapper extends RepeatableHttpServletRequestWrapper {

    /**
     * 没被包装过的HttpServletRequest（特殊场景,需要自己过滤）
     */
    private final HttpServletRequest orgRequest;
    /**
     * html过滤
     */
    private final static XssHtmlFilter HTML_FILTER = new XssHtmlFilter();

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    @Override
    protected void initBody() throws IOException {
        if (body != null) {
            return;
        }
        try (StringWriter writer = new StringWriter();
             BufferedReader reader = this.getRequest().getReader()) {
            int read;
            char[] buf = new char[1024 * 8];
            while ((read = reader.read(buf)) != -1) {
                writer.write(buf, 0, read);
            }
            this.body = xssEncode(writer.getBuffer().toString()).getBytes();
        }
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (StringUtils.hasText(value)) {
            value = xssEncode(value);
        }
        return value;
    }



    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = xssEncode(parameters[i]);
        }
        return parameters;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> map = new LinkedHashMap<>();
        Map<String, String[]> parameters = super.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = xssEncode(values[i]);
            }
            map.put(key, values);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (StringUtils.hasText(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    private String xssEncode(String input) {
        return HTML_FILTER.filter(input);
    }

    /**
     * 获取初始request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取初始request
     *
     * @param request request
     * @return HttpServletRequest
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) request).getOrgRequest();
        }
        return request;
    }

}
