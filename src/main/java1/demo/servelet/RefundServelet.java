package demo.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import adyen.RefundClient;
import util.JsonTool;

/**
 * Servlet implementation class ReturnActionServelet
 */
@WebServlet(name="refundServelet", urlPatterns="/refund")  
public class RefundServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RefundServelet() {
		// TODO Auto-generated constructor stub
	}
   
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(".............................Refund.............................");
		String paymentId = request.getParameter("paymentId");
		String resultJson = RefundClient.request(paymentId);
        JsonTool tool = new JsonTool();  
        System.out.println(tool.stringToJSON(resultJson));
		request.setAttribute("resultJson",tool.stringToJSON(resultJson));
		request.getRequestDispatcher("/refund.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
