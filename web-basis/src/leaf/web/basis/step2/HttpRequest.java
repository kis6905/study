package leaf.web.basis.step2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
	
	public enum HttpMethod {
		GET,
		POST,
		PUT,
		DELETE;
		
		public static boolean isGet(String method) {
			return GET.name().equalsIgnoreCase(method);
		}
	}
	
	public HttpRequest(InputStream input) {
		parse(input);
	}
	
	private void parse(InputStream input) {
		try {
			String requestRaw = read(input);
			String[] requestLines = requestRaw.toString().split("\n");
			RequestLineType lineType = RequestLineType.REQUEST_LINE;
			for (int idx = 0; idx < requestLines.length; idx++) {
				String line = requestLines[idx];
				if (idx == 0) {
					parseRequestLine(line);
					lineType = RequestLineType.HEADER_LINE;
					continue;
				} else if (line.isEmpty()) {
					lineType = RequestLineType.BODY_LINE;
					continue;
				}
				
				switch (lineType) {
				case HEADER_LINE:
					
					break;
				case BODY_LINE:
					
					break;
				default:
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
	
	private void parseRequestLine(String line) throws Exception {
		String[] requestInfos = line.split(" ");
		this.method = requestInfos[0];
		
		String[] uriInfos = requestInfos[1].split("\\?");
		this.uri = uriInfos[0];
		
		if (HttpMethod.isGet(this.method) && uriInfos.length >= 2) {
			parseQueryString(uriInfos[1]);
		}
	}
	
	private void parseQueryString(String queryString) {
		String[] params = queryString.split("\\&");
		this.parameters = new HashMap<>();
		for (String param : params) {
			String[] pair = param.split("=");
			this.parameters.put(pair[0], pair[1]);
		}
	}
	
	private void parseAndAddHeader(String line) {
		
	}
	
	private void parseRequestBody(String line) {
		
	}
	
	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public String getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "HttpRequest [uri=" + uri + ", method=" + method + ", headers="
				+ headers + ", parameters=" + parameters + ", cookies=" + cookies + ", body=" + body + "]";
	}
	
}
