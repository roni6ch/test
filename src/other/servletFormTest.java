package other;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servletTest
 */
@WebServlet("/servletTest")
public class servletFormTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletFormTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		if (width != null && height != null){
			try{
				double w=Double.parseDouble(width);
				double h=Double.parseDouble(height);
				double sumWH = w*h;
				out.println("Servlet sum: " + sumWH);
			}
			catch(NumberFormatException e){
				out.println("the numbers are wrong!" );
			}
		}else{
			out.println("SERVLET should receive 2 numbers" );
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
