package demo.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adyen.PaymentSessionClient;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DemoServelet
 */
@WebServlet(name="demoServelet", urlPatterns="/pay")  
public class DemoServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DemoServelet() {
		// TODO Auto-generated constructor stub
	}
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseJson = PaymentSessionClient.request();
		JSONObject json = JSONObject.fromObject(responseJson);
		request.setAttribute("paymentSession",json);
		request.getRequestDispatcher("/payment.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
