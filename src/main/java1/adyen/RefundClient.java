package adyen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class RefundClient {

	public static String request(String id) throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/payments/" + id);

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);

		String data = "" + "authentication.userId=8a82941862ba0e370162cd88239d1e2c"
				+ "&authentication.entityId=8a82941862ba0e370162cd88d5b91e32" + "&authentication.password=M79C5PzgG8"
				+ "&amount=1.00" + "&currency=ZAR" + "&paymentType=RF";

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		int responseCode = conn.getResponseCode();
		InputStream is;

		if (responseCode >= 400)
			is = conn.getErrorStream();
		else
			is = conn.getInputStream();

		return IOUtils.toString(is);
	}


}
