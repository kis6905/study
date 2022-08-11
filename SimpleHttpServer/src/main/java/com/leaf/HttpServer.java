package com.leaf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

// thread
// cookie
// session
public class HttpServer {

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
                if (flagOfRequestLine == 0) {
                    flagOfRequestLine = 1;
                    httpRequest.parseRequestLine(line);
                } else if (flagOfRequestLine == 1) {

                }
            }

            String name = httpRequest.getParameter("name");
            String age = httpRequest.getParameter("age");

            System.out.println("name: " + name);
            System.out.println("age: " + age);

        }

    }

}
