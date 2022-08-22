package com.leaf;

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

            StringBuilder responseStringBuilder = new StringBuilder();
            responseStringBuilder.append("HTTP/1.1 200 OK").append("\n");
            responseStringBuilder.append("jwt: token").append("\n");
            responseStringBuilder.append("content-type: text/html;").append("\n");

            String sessionId = httpRequest.getCookie("sessionId");
            HttpSession httpSession = null;
            if (sessionId == null || sessionId.isEmpty()) {
                sessionId = UUID.randomUUID().toString().replaceAll("-", "");
                responseStringBuilder.append("set-cookie: sessionId=").append(sessionId).append("\n");

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

            responseStringBuilder.append("\n");
            responseStringBuilder.append("<html>");
            responseStringBuilder.append("<head>");
            responseStringBuilder.append("</head>");
            responseStringBuilder.append("<body>");
            responseStringBuilder.append("  <h1>hello simple server!</h1>");
            responseStringBuilder.append("</body>");
            responseStringBuilder.append("</html>");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(responseStringBuilder.toString());
            bw.flush();
            bw.close();
        }

    }

}
