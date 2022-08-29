package com.leaf.http;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    public static final String REQUEST_HEADER_FINISH_LINE = "\r";

    public static final Map<String, HttpSession> sessionMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);

        ExecutorService es = Executors.newFixedThreadPool(3);

        while (true) {
            System.out.println("\n==============================================");
            Socket socket = serverSocket.accept();
            es.submit(new HttpThread(socket));
        }

    }

}
