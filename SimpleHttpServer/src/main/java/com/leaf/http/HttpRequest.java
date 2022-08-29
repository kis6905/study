package com.leaf.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private Map<String, String> headers;
    private Map<String, String> parameters;
    private Map<String, String> cookies;
    private String method;
    private String requestUri;

    public HttpRequest() {
        headers = new HashMap<>();
        parameters = new HashMap<>();
        cookies = new HashMap<>();
    }

    public void parseRequestLine(String line) {
        String[] requestLineToken = line.split(" ");
        this.setMethod(requestLineToken[0]);

        String[] urlToken = requestLineToken[1].split("\\?");
        this.setRequestUri(urlToken[0]);

        if (urlToken.length == 1) {
            return;
        }

        String[] parameters = urlToken[1].split("\\&");
        for (String pair : parameters) {
            String[] keyValue = pair.split("\\=");
            if (keyValue.length < 2) {
                continue;
            }
            this.addParameter(keyValue[0], keyValue[1]);
        }
    }

    public void parseHeaderLine(String line) {
        int index = line.indexOf(':');
        String name = line.substring(0, index).trim();
        String value = line.substring(index + 1).trim();

        if ("cookie".equalsIgnoreCase(name)) {
            parseCookie(value);
        } else {
            addHeader(name, value);
        }
    }

    public void parseCookie(String line) {
        String[] cookies = line.split(";");
        for (String cookie : cookies) {
            String[] pair = cookie.split("=");
            addCookie(pair[0], pair[1]);
        }
    }

    public void addHeader(String name, String value) {
        headers.put(name.toLowerCase(), value);
    }

    public String getHeader(String name) {
        return headers.get(name.toLowerCase());
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }

    public void addCookie(String name, String value) {
        cookies.put(name.trim(), value.trim());
    }

    public String getCookie(String name) {
        return cookies.get(name);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestUri() {
        return this.requestUri;
    }

}
