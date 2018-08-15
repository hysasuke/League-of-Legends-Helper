import java.io.*;
import java.net.*;


public class URLContent {
private URL url;
private URLConnection conn;
private long contentLength;

	public URLContent(String link) throws IOException  {
		url = new URL(link);
		conn = url.openConnection();
		contentLength = conn.getContentLengthLong();
	}
	
	public String getContent() throws IOException {
			InputStream input = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			return reader.readLine();
	}
	
	public boolean isContent() {
		return contentLength > 0;
	}
	

}
