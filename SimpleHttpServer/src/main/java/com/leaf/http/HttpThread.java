package com.leaf.http;

import com.leaf.http.enums.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.UUID;

public class HttpThread implements Runnable {

    private Socket socket;

    public HttpThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
                    if (HttpServer.REQUEST_HEADER_FINISH_LINE.equals(line)) {
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

                HttpServer.sessionMap.put(sessionId, httpSession);
            } else {
                httpSession = HttpServer.sessionMap.get(sessionId);
                if (httpSession == null) {
                    httpSession = new HttpSession();
                }
                System.out.println("session: " + httpSession.toString());
            }

            Thread.sleep(5000);

            httpResponse.sendResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
