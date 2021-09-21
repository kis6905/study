package leaf.web.basis.step1;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

	private static final int BUFFER_SIZE = 4096;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Start http server...\n");
		while (true) {
			Socket socket = serverSocket.accept();
			
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			
			byte[] buffer = new byte[BUFFER_SIZE];
			StringBuilder requestBuffer = new StringBuilder();
			
			int i = 0;
			while (true) {
				buffer = new byte[BUFFER_SIZE];
	            i = input.read(buffer);
		        for (int j = 0; j < i; j++) {
		            requestBuffer.append((char) buffer[j]);
		        }
		        
		        if (input.available() <= 0) {
		        	break;
		        }
			}
			
			System.out.println("request: ");
			System.out.println(requestBuffer.toString());
			
			Thread.sleep(2000);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
			bw.write("HTTP/1.1 200 OK\n");
			bw.write("content-type: text/html;\n");
			bw.write("\n");
			bw.write("<h1>hello world</h1>");
			
			bw.flush();
			input.close();
			bw.close();
			socket.close();
			
			System.out.println("\n\n");
		}
	}

}
