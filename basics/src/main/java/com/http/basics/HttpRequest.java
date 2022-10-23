package com.http.basics;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequest {
    private String method;
    private String url;
    private String version;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();

    public void parseRequestLine(String line) {
        String[] info = line.split(" ");
        this.method = info[0];
        this.url = info[1];
        this.version = info[2];

        if (this.method.equalsIgnoreCase("GET")) {
            if (!this.url.contains("?")) {
                return;
            }

            String[] temp = this.url.split("\\?");
            parseQueryString(temp[1]);
        }
    }

    public void parseQueryString(String query) {
        // key=value&key2=value2 ...
        String[] params = query.split("\\&");
        for (String param : params) {
            String[] pair = param.split("=");
            parameters.put(pair[0], pair[1]);
        }
    }

    public void parseHeaderLine(String line) {
        String[] pair = line.split(":");
        headers.put(pair[0].toLowerCase().trim(), pair[1].trim());

        /*
         * TODO:
         *  header name 이 cookie 인 경우 cookie 를 파싱해 cookies 에 put 하세요.
         */
    }

    public String getHeader(String headerName) {
        return headers.get(headerName.toLowerCase());
    }

    public String getParameter(String parameterName) {
        return parameters.get(parameterName);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String toStringHeaders() {
        return headers.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", headersSize='" + headers.size() + '\'' +
                '}';
    }
}
