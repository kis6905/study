package leaf.web.basis.step2;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("[" + Thread.currentThread().getName() + "] " + "Start http server...\n");
		while (true) {
			Socket socket = serverSocket.accept();
			
			Thread servletThread = new ServletThread(socket);
			servletThread.start();
			
			System.out.println("\n\n");
		}
	}

}
