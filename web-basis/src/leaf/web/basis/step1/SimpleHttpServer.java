package leaf.web.basis.step1;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("Start http server...\n");
		
		while (true) {
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			byte[] buffer = new byte[2048];
			StringBuilder requestBuffer = new StringBuilder();
			
			int i = 0;
			while (input.available() > 0) {
				buffer = new byte[2048];
	            i = input.read(buffer);
		        for (int j = 0; j < i; j++) {
		            requestBuffer.append((char) buffer[j]);
		        }
			}
			
			System.out.println("request: ");
			System.out.println(requestBuffer.toString());
			System.out.println("-----------------------------");
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
//			bw.write("HTTP/1.1 200 OK\n");
//			bw.write("content-type: text/plain;\n");
//			bw.write("\n");
//			bw.write("<h1>hello world</h1>\n");
			
			String response = """
					HTTP/1.1 200 OK
					content-type: text/html;charset=utf8;
					set-cookie: mycookie=abc; cookie2=bbb;
					
					<h1>hello world</h1>
					<form action="/user" method="post">
						<input type="text" name="name" value="leaf" /><br/>
						<input type="text" name="addr" value="goyang" /><br/>
						<input type="file" name="file" /><br/>
						<button type="submit">전송</submit>
					</form>
					""";
			bw.write(response);
			bw.flush();
			
			bw.close();
			input.close();
			socket.close();
			
			System.out.println("\n\n");
		}
	}

}
