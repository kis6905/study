package leaf.web.basis.step2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServletThread extends Thread {
	private static final String REQUEST_THREAD_PREFIX = "request_";
	
	private Socket socket;
	
	public ServletThread(Socket socket) {
		super(REQUEST_THREAD_PREFIX);
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try (
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(output));
		) {
			HttpRequest httpRequest = new HttpRequest(input);
			
			bw.write("HTTP/1.1 200 OK\n");
			bw.write("content-type: text/html;\n");
			bw.write("\n");
			bw.write("<h1>hello world</h1>");
			bw.flush();
			
			System.out.println("\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { socket.close(); } catch (IOException e) {}
		}
	}

}
