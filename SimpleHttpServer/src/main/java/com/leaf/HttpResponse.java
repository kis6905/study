package com.leaf;

import com.leaf.enums.HttpStatus;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private OutputStream outputStream;
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private HttpStatus httpStatus;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void addHeader(String name, String value) {
        headers.put(name.toLowerCase(), value);
    }

    public void addCookie(String name, String value) {
        cookies.put(name.trim(), value.trim());
    }

    public void sendResponse() {
        try (
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf8"));
        ) {
            StringBuilder responseStringBuilder = new StringBuilder();

            // Status Line
            responseStringBuilder.append("HTTP/1.1 ")
                    .append(httpStatus.getStatus() + " ")
                    .append(httpStatus.name()).append("\n");

            // Header Line
            headers.entrySet().stream().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                responseStringBuilder.append(key).append(":").append(value).append("\n");
            });

            // Add cookies to the header
            cookies.entrySet().stream().forEach(cookie -> {
                responseStringBuilder
                        .append("set-cookie:")
                        .append(cookie.getKey())
                        .append("=")
                        .append(cookie.getValue())
                        .append("\n");
            });

            // Header 와 Body 구분 Line
            responseStringBuilder.append("\n");

            // Body
            responseStringBuilder.append(makeBody());

            bw.write(responseStringBuilder.toString());
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeBody() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("  <h1>hello simple server!</h1>");
        sb.append("  <form action=\"/user\" method=\"POST\" enctype=\"multipart/form-data\">");
        sb.append("    <input type=\"text\" name=\"name\" />");
        sb.append("    <input type=\"text\" name=\"age\" />");
        sb.append("    <button type=\"submit\">저장</button>");
        sb.append("  </form>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}
