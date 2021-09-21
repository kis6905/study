package leaf.web.basis.step2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HttpRequest {
	private static final int BUFFER_SIZE = 4096;
	
	private String uri;
	private String method;
	private Map<String, String> headers;
	private Map<String, String> parameters;
	private Map<String, String> cookies;
	private String body;
	
	private enum RequestLineType {
		REQUEST_LINE,
		HEADER_LINE,
		BODY_LINE
	}
	
	public HttpRequest(InputStream input) {
		parse(input);
	}
	
	private void parse(InputStream input) {
		try {
			String requestRaw = read(input);
			
			String[] requestLines = requestRaw.toString().split("\n");
			for (int idx = 0; idx < requestLines.length; idx++) {
				String line = requestLines[idx];
				RequestLineType lineType = null;
				if (idx == 0) {
					parseRequestLine(line);
					lineType = RequestLineType.HEADER_LINE;
				} else if (line != null && line.isEmpty()) {
					lineType = RequestLineType.BODY_LINE;
				}
				
				switch (lineType) {
				case HEADER_LINE:
					
					break;
				case BODY_LINE:
					
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String read(InputStream input) throws IOException {
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
		return requestBuffer.toString();
	}
	
	private void parseRequestLine(String line) {
		
	}
	
	private void parseAndAddHeader(String line) {
		
	}
	
	private void parseRequestBody(String line) {
		
	}
}
