package adyen;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by qiangli3 on 4/16/18.
 */
public class PaymentResultClient {
	
	static final String ENDPOINT_TEST = "https://checkout-test.adyen.com/services/PaymentSetupAndVerification";
	static final String VERSION = "/v37";
	static final String PAYMENTSRESULT = "/payments/result";
	
	public static String request(String paymentData) throws IOException {
		URL url = new URL(ENDPOINT_TEST + VERSION + PAYMENTSRESULT);
		
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("X-Api-Key", "AQEmhmfuXNWTK0Qc+iSdk3Yzs/SXSYpZHspu73GjS69muiVhl1Pl5kcQwV1bDb7kfNy1WIxIIkxgBw==-7unSb5EY6M//GpYJcdXJemPbe+UOWSRoeDqsE2UR6vk=-7HAypPj4K6cEcFRI");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Length", paymentData.length() + "");

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(paymentData);
		wr.flush();
		wr.close();
		int responseCode = conn.getResponseCode();
		InputStream is;

		if (responseCode >= 400)
			is = conn.getErrorStream();
		else
			is = conn.getInputStream();

		return org.apache.commons.io.IOUtils.toString(is);
	}
		
}
