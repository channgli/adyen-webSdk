package payment;

import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.net.URL;

public class GetPayment {
	public static String getPayment(String path) throws IOException {
		URL url = new URL("https://test.oppwa.com" + path 
				+ "?authentication.userId=8a82941862ba0e370162cd88239d1e2c" + "&authentication.password=M79C5PzgG8"
				+ "&authentication.entityId=8a82941862ba0e370162cd88d5b91e32");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		InputStream is;

		if (responseCode >= 400)
			is = conn.getErrorStream();
		else
			is = conn.getInputStream();

		return IOUtils.toString(is);
	}
}
