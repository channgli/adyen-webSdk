package demo.servelet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONObject;

import adyen.PaymentResultClient;
import util.JsonTool;

/**
 * Servlet implementation class ReturnActionServelet
 */
@WebServlet(name="returnActionServelet", urlPatterns="/paymentResult")  
public class ReturnActionServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ReturnActionServelet() {
		// TODO Auto-generated constructor stub
	}
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String payload = request.getParameter("payload");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("payload", payload);
		
		JSONObject jsonObj = new JSONObject(map);
        String data = jsonObj.toString();
		String resultJson = PaymentResultClient.request(data);
        JsonTool tool = new JsonTool();  
        
        System.out.println(tool.stringToJSON(resultJson));
		request.setAttribute("resultJson",tool.stringToJSON(resultJson));
		response.getWriter().write(resultJson);
	}

}
