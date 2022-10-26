package com.http.basics;

import com.http.basics.constants.RequestLineType;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);

        ExecutorService es = Executors.newFixedThreadPool(4);

        while (true) {
            System.out.println("\n=========================");
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();

            es.execute(() -> {
                try {
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    byte[] data = new byte[4096];
                    int nRead = 0;
                    while (true) {
                        nRead = is.read(data);
                        if (nRead <= 0) {
                            break;
                        }
                        buffer.write(data, 0, nRead);
                        if (nRead < 4096) {
                            break;
                        }
                    }
                    buffer.flush();

                    String requestMessage = new String(buffer.toByteArray(), StandardCharsets.UTF_8);

                    String[] lines = requestMessage.split(LINE_SEPARATOR);
                    RequestLineType requestLineType = RequestLineType.REQUEST_LINE;
                    HttpRequest httpRequest = new HttpRequest();

                    for (String line : lines) {
//                System.out.println(line);

                        if (requestLineType == RequestLineType.REQUEST_LINE) {
                            httpRequest.parseRequestLine(line);
                            requestLineType = RequestLineType.HEADER_LINE;
                        } else if (requestLineType == RequestLineType.HEADER_LINE) {
                            if (line.equals(LINE_SEPARATOR) || line.isEmpty()) { // blank line
                                requestLineType = RequestLineType.BODY_LINE;
                                continue;
                            }
                            httpRequest.parseHeaderLine(line);
                        } else if (requestLineType == RequestLineType.BODY_LINE) {
                            /*
                             * TODO:
                             *  request method 가 POST 또는 PUT 일 때만 body 를 확인하고,
                             *  Content-Type 이 application/x-www-form-urlencoded 인 경우만 파싱하세요.
                             *  그 외 content-type 은 Http Status 400 (BAD_REQUEST) 를 반환해주세요.
                             */
                        }
                    }

                    String threadName = Thread.currentThread().getName();
                    System.out.println("[" + threadName + "] " + httpRequest.toString());
//                    System.out.println(httpRequest.getHeader("cookie"));
//                    System.out.println(httpRequest.getParameter("name"));

                    if (!httpRequest.getUrl().contains("favicon")) {
                        Thread.sleep(1000 * 3);
                    }

                    HttpResponse httpResponse = new HttpResponse(socket.getOutputStream());
                    httpResponse.addHeader("set-cookie", "foo2=bar2");
                    httpResponse.addHeader("Access-Control-Allow-Origin", "https://www.naver.com");
                    httpResponse.sendResponse();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
