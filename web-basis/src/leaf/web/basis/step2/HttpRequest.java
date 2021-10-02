package leaf.web.basis.step2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private static final int BUFFER_SIZE = 4096;
	
	private String uri;
	private String method;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, String> parameters = new HashMap<>();
	private Map<String, String> cookies = new HashMap<>();
	private String body = "";
	
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
				} else if (line.equals("\n")) {
					lineType = RequestLineType.BODY_LINE;
					continue;
				}
				
				switch (lineType) {
				case HEADER_LINE:
					parseAndAddHeader(line);
					break;
				case BODY_LINE:
					appendRequestBody(line);
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
		StringBuilder requestBuilder = new StringBuilder();
		int i = 0;
		while (input.available() > 0) {
			buffer = new byte[BUFFER_SIZE];
            i = input.read(buffer);
	        for (int j = 0; j < i; j++) {
	        	requestBuilder.append((char) buffer[j]);
	        }
		}
		return requestBuilder.toString();
	}
	
	private void parseRequestLine(String line) throws Exception {
		String[] requestInfos = line.split(" ");
		if (requestInfos.length < 3) {
			return;
		}
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
		try {
			String[] pair = line.split("\\:");
			String key = pair[0].trim();
			String value = pair[1].trim();
			
			this.headers.put(key, value);
			
			if (key.equalsIgnoreCase("cookie")) {
				
			}		
		} catch (Exception e) {
		}
	}
	
	private void appendRequestBody(String line) {
		this.body += line;
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
