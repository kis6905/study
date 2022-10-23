package com.http.basics;

import com.http.basics.constants.ResponseStatus;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private OutputStream outputStream;
    private ResponseStatus responseStatus;
    private Map<String, String> headers = new HashMap<>();

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.responseStatus = ResponseStatus.OK;
    }

    public HttpResponse(OutputStream outputStream, ResponseStatus responseStatus) {
        this.outputStream = outputStream;
        this.responseStatus = responseStatus;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void sendResponse() throws Exception {
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(outputStream, "utf8"));

        StringBuilder stringBuilder = new StringBuilder();

        // Status Line
        stringBuilder.append("HTTP/1.1 ")
                     .append(responseStatus.getStatusCode()).append(" ")
                     .append(responseStatus.getStatusMessage()).append(HttpServer.LINE_SEPARATOR);

        // Header
        headers.entrySet().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key).append(":").append(value).append(HttpServer.LINE_SEPARATOR);
        });

        // Blank Line
        stringBuilder.append(HttpServer.LINE_SEPARATOR);

        // Body
        stringBuilder.append("<h1>hello world</h1>").append(HttpServer.LINE_SEPARATOR);
        stringBuilder.append("<form action=\"/login\" method=\"post\">").append(HttpServer.LINE_SEPARATOR);
        stringBuilder.append("  <input type=\"text\" name=\"userId\"><br/>").append(HttpServer.LINE_SEPARATOR);
        stringBuilder.append("  <input type=\"password\" name=\"password\"><br/>").append(HttpServer.LINE_SEPARATOR);
        stringBuilder.append("  <button type=\"submit\">login</button>").append(HttpServer.LINE_SEPARATOR);
        stringBuilder.append("</form>").append(HttpServer.LINE_SEPARATOR);

        br.write(stringBuilder.toString());
        br.flush();
        br.close();
    }

}
