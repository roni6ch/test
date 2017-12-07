package test;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


/**
 * Servlet implementation class servletControll
 */
@WebServlet("/servletController")
public class servletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	HttpSession session;
	ArrayList<Item> listOfItems;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletController() {
		super();
	}

	/**
	 * @return 
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		ServletContext sc = getServletContext();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		if (action != null && action.equalsIgnoreCase("login")){

			//	String googleId = request.getParameter("googleId");
			String userName = request.getParameter("name");
			String userimage = request.getParameter("image");
			String userEmail = request.getParameter("email");

			session = request.getSession();
			listOfItems = dao.getItems();
			session.setAttribute("listOfItems", listOfItems);
			session.setAttribute("userName", userName);
			session.setAttribute("userimage", userimage);
			session.setAttribute("userEmail", userEmail);
			System.out.println("hello");
			//sc.getRequestDispatcher("/main.jsp").forward(request, response);
			request.getRequestDispatcher("main.jsp").forward(request, response);

		}
		else if (action != null && action.equalsIgnoreCase("addItem")){
			String date = request.getParameter("date");
			String note = request.getParameter("note");
			if (dao.addItem(date,note)){
				listOfItems = dao.getItems();
				session.setAttribute("listOfItems", listOfItems);
				String listOfItemsParsed = new Gson().toJson(listOfItems);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(listOfItemsParsed);
				out.flush();
			}
			else{	
				out.println(false);
				out.flush();
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				System.out.println("error while tring to add note: " + note);
			}
		}
		else if (action != null && action.equalsIgnoreCase("removeItem")){
			String id = request.getParameter("id");
			System.out.println("remove id: " + id);
			if (dao.removeItem(id)){
				listOfItems = dao.getItems();
				session.setAttribute("listOfItems", listOfItems);
				sc.getRequestDispatcher("/main.jsp").forward(request, response);
			}
			else{
				System.out.println("error while tring to delete id: " + id);
			}
		}
		else if (action != null && action.equalsIgnoreCase("doneItem")){
			String id = request.getParameter("id");
			String checked = request.getParameter("checked");
			if (dao.doneItem(id,checked)){
				listOfItems = dao.getItems();
				session.setAttribute("listOfItems", listOfItems);
				sc.getRequestDispatcher("/main.jsp").forward(request, response);
			}
			else{
				System.out.println("error while tring to delete id: " + id);
			}
		}
		else if (action != null && action.equalsIgnoreCase("updateItem")){
			String id = request.getParameter("id");
			String updatedNote = request.getParameter("updatedNote");
			if (dao.updateItem(id,updatedNote)){
				listOfItems = dao.getItems();
				session.setAttribute("listOfItems", listOfItems);
				sc.getRequestDispatcher("/main.jsp").forward(request, response);
			}
			else{
				System.out.println("error while tring to update id: " + id);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
