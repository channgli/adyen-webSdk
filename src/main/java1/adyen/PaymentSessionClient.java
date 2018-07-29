package adyen;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import bean.Amount;
import util.JsonTool;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qiangli3 on 4/16/18.
 */
public class PaymentSessionClient {
	
	static final String ENDPOINT_TEST = "https://checkout-test.adyen.com/services/PaymentSetupAndVerification";
	static final String VERSION = "/v37";
	static final String PAYMENTSESSION = "/paymentSession";
	static final String PAYMENTSRESULT = "/payments/result";
	
	public static String request() throws IOException {
		URL url = new URL(ENDPOINT_TEST + VERSION + PAYMENTSESSION);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setRequestProperty("X-Api-Key", "AQEmhmfuXNWTK0Qc+iSdk3Yzs/SXSYpZHspu73GjS69muiVhl1Pl5kcQwV1bDb7kfNy1WIxIIkxgBw==-7unSb5EY6M//GpYJcdXJemPbe+UOWSRoeDqsE2UR6vk=-7HAypPj4K6cEcFRI");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Amount amount =  Amount.builder().currency("PLN").value("300").build();
		
		List<String> array = new ArrayList<>();
		array.add("dotpay");
		map.put("amount", amount);
		map.put("channel", "Web");
		map.put("countryCode", "PL");
		map.put("shopperReference", "yourShopperId_IOfW3k9G2PvXFu2j");
		map.put("shopperLocale", "pl-PL");
		map.put("reference", "87387");
		map.put("sdkVersion", "1.3.0");
		map.put("enableOneClick", true);
		map.put("enableRecurring", false);
		map.put("origin", "http://localhost:8080");
		map.put("allowedPaymentMethods", array);
		map.put("merchantAccount", "GumtreePL");
		map.put("returnUrl",  "http://localhost:8080/checkout/result");
		
        JSONObject jsonObj = new JSONObject(map);
        String data = jsonObj.toString();
        JsonTool tool = new JsonTool();  
        System.out.println(tool.stringToJSON(data));

		conn.setRequestProperty("Content-Length", data.length() + "");

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

		return org.apache.commons.io.IOUtils.toString(is);
	}
	
	public static void main(String[] args) throws IOException {
		String response = request();
		System.out.println(response);
	}	
}
