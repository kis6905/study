package com.leaf;

import com.leaf.enums.HttpStatus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpServer {

    public static final String REQUEST_HEADER_FINISH_LINE = "\r";

    public static final Map<String, HttpSession> sessionMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            System.out.println("\n==============================================");
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            ByteArrayOutputStream target = new ByteArrayOutputStream();

            byte[] buffer = new byte[2048];
            int offset = 0;
            while (true) {
                int length = is.read(buffer, offset, 2048);
                if (length > 0) {
                    target.write(buffer, 0, length);
                }
                if (length < 2048) {
                    break;
                }
            }

            byte[] requestBytes = target.toByteArray();
            String request = new String(requestBytes);

            HttpRequest httpRequest = new HttpRequest();

            String[] lines = request.split("\\n");
            int flagOfRequestLine = 0; // 0: requestLine, 1: header, 2: body
            for (String line : lines) {
                System.out.println(line);
                if (line.isEmpty()) {
                    continue;
                }
                if (flagOfRequestLine == 0) {
                    flagOfRequestLine = 1;
                    httpRequest.parseRequestLine(line);
                } else if (flagOfRequestLine == 1) {
                    if (REQUEST_HEADER_FINISH_LINE.equals(line)) {
                        flagOfRequestLine = 2;
                        continue;
                    }
                    httpRequest.parseHeaderLine(line);
                } else if (flagOfRequestLine == 2) {
                    // TODO: body 파싱
                }
            }

            System.out.println("-----------------------------");
            System.out.println("name: " + httpRequest.getParameter("name"));
            System.out.println("age: " + httpRequest.getParameter("age"));

            String sessionId = httpRequest.getCookie("sessionId");
            HttpSession httpSession = null;

            HttpResponse httpResponse = new HttpResponse(socket.getOutputStream());
            httpResponse.setHttpStatus(HttpStatus.OK);
            httpResponse.addHeader("doobyeol", "girlfriend");
            httpResponse.addHeader("content-type", "text/html;charset=utf8");

            if (sessionId == null || sessionId.isEmpty()) {
                sessionId = UUID.randomUUID().toString().replaceAll("-", "");
                httpResponse.addCookie("sessionId", sessionId);

                httpSession = new HttpSession();
                httpSession.setAttribute("loginId", "leaf");

                sessionMap.put(sessionId, httpSession);
            } else {
                httpSession = sessionMap.get(sessionId);
                if (httpSession == null) {
                    httpSession = new HttpSession();
                }
                System.out.println("session: " + httpSession.toString());
            }

            httpResponse.sendResponse();
        }

    }

}
